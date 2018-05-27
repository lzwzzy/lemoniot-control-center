package com.lzw.lemoniot.handler;


import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.lzw.lemoniot.Mqtt;
import com.lzw.lemoniot.builder.TextBuilder;
import com.lzw.lemoniot.dao.DeviceRepository;
import com.lzw.lemoniot.dao.RoomRepository;
import com.lzw.lemoniot.dao.UserRepository;
import com.lzw.lemoniot.modal.Device;
import com.lzw.lemoniot.modal.Room;
import com.lzw.lemoniot.modal.User;
import com.lzw.lemoniot.service.DeviceService;
import me.chanjar.weixin.common.session.WxSessionManager;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.message.WxMpXmlMessage;
import me.chanjar.weixin.mp.bean.message.WxMpXmlOutMessage;
import org.eclipse.paho.client.mqttv3.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.Base64Utils;

import java.nio.charset.Charset;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * DeviceMsgHandler
 *
 * @author lzw
 * @date 2018/4/24 15:16
 **/
@Component
public class DeviceMsgHandler extends AbstractHandler {


    @Autowired
    private DeviceService deviceService;

    @Autowired
    private DeviceRepository deviceRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoomRepository roomRepository;

    @Autowired
    private Mqtt mqtt;

    @Override
    public WxMpXmlOutMessage handle(WxMpXmlMessage wxMpXmlMessage,
                                    Map<String, Object> map,
                                    WxMpService wxMpService,
                                    WxSessionManager wxSessionManager) {
        byte[] bytes = Base64Utils.decodeFromString(wxMpXmlMessage.getContent());
        String device_info = new String(bytes, Charset.defaultCharset());
        Gson gson = new Gson();
        Device device = gson.fromJson(device_info, new TypeToken<Device>() {}.getType());
        User user = new User();
        if (userRepository.existsByOpenId(wxMpXmlMessage.getOpenId())){
            user = userRepository.findByOpenId(wxMpXmlMessage.getOpenId());
        }else {
            user.setOpenId(wxMpXmlMessage.getOpenId());
            user.setName(wxMpXmlMessage.getLocationName());
            user.setHeadImgUrl(wxMpXmlMessage.getPicUrl());
            user = userRepository.saveAndFlush(user);
        }
        if (device == null) {
            device = new Device();
        }
        device.setWechatDeviceId(wxMpXmlMessage.getDeviceId());

        if (!deviceService.deviceIsExists(device.getWechatDeviceId())){
            device = deviceRepository.save(device);
        }else {
            device = deviceRepository.findByWechatDeviceId(wxMpXmlMessage.getDeviceId());
        }
        MqttClient client = mqtt.getClient();
        MqttTopic mqttTopic = client.getTopic("device/event");
        MqttMessage mqttMessage = new MqttMessage();

        switch (wxMpXmlMessage.getEvent()) {
            case "bind":
                deviceService.bindDeviceToUser(device, user);
                Room room = roomRepository.findByUserAndType(user, "默认房间类型");
                device.setRoom(room);
                deviceService.bindDeviceToRoom(device, room);
                logger.info("绑定成功");
                mqttMessage.setQos(1);
                mqttMessage.setPayload("ok".getBytes());
                mqttMessage.setRetained(true);
                return new TextBuilder().build("ok", wxMpXmlMessage, wxMpService);
            case "unbind":
//                if (deviceRepository.existsByWechatDeviceId(device.getWechatDeviceId()))
                deviceService.unbindDeviceFromUser(user, device);
                logger.info("解绑成功");
                mqttMessage.setQos(1);
                mqttMessage.setPayload("ok".getBytes());
                mqttMessage.setRetained(true);
                break;
            default:
                break;
        }
        try {
            //通知设备绑定成功
            mqttTopic.publish(mqttMessage);
        } catch (MqttException e) {
            e.printStackTrace();
        }
        return null;
    }

}
