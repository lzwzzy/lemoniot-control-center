package com.lzw.lemoniot.handler;


import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.lzw.lemoniot.builder.TextBuilder;
import com.lzw.lemoniot.modal.Device;
import com.lzw.lemoniot.modal.User;
import com.lzw.lemoniot.service.DeviceService;
import me.chanjar.weixin.common.session.WxSessionManager;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.message.WxMpXmlMessage;
import me.chanjar.weixin.mp.bean.message.WxMpXmlOutMessage;
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

    @Override
    public WxMpXmlOutMessage handle(WxMpXmlMessage wxMpXmlMessage,
                                    Map<String, Object> map,
                                    WxMpService wxMpService,
                                    WxSessionManager wxSessionManager) {
        switch (wxMpXmlMessage.getEvent()) {
            case "bind":
                byte[] bytes = Base64Utils.decodeFromString(wxMpXmlMessage.getContent());
                String device_info = new String(bytes, Charset.defaultCharset());
                Gson gson = new Gson();
                Device device = gson.fromJson(device_info, new TypeToken<Device>() {}.getType());
                User user = new User();
                user.setId(wxMpXmlMessage.getOpenId());
                user.setName(wxMpXmlMessage.getLocationName());
                if (device == null) {
                    device = new Device();
                }
                device.setId(wxMpXmlMessage.getDeviceId());
                Set<Device> devices = new HashSet<>();
                devices.add(device);
                user.setDevices(devices);
                deviceService.bindDevice(user);
                logger.info("绑定成功");
                return new TextBuilder().build("ok", wxMpXmlMessage, wxMpService);
            case "unbind":
                logger.info("解绑成功");
                break;
            default:
                break;
        }
        return null;
    }

}
