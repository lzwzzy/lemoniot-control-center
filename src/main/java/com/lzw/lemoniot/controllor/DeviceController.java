package com.lzw.lemoniot.controllor;

import com.lzw.lemoniot.Mqtt;
import com.lzw.lemoniot.common.utils.R;
import com.lzw.lemoniot.utils.JsonUtils;
import org.eclipse.paho.client.mqttv3.*;
import org.fusesource.mqtt.client.BlockingConnection;
import org.fusesource.mqtt.client.MQTT;
import org.fusesource.mqtt.client.QoS;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.net.URISyntaxException;
import java.util.Map;

/**
 * DeviceController
 *
 * @author lzw
 * @date 2018/5/17 14:22
 **/
@RestController
@CrossOrigin
@RequestMapping("/device/event")
public class DeviceController {


    private final Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    private Mqtt mqtt;

    private MqttMessage message;

    private MqttTopic mqttTopic;


    @PostMapping("/gateway")
    public R gatewayEvent(@RequestBody Map requestBody){

        MqttClient mqttClient = mqtt.getClient();
        byte[] payload = JsonUtils.toJson(requestBody).getBytes();
        String topic = "device/event/gateway";
        mqttTopic = mqttClient.getTopic(topic);
        message = new MqttMessage();
        message.setQos(0);
        message.setPayload(payload);
        message.setRetained(true);
        try {
            MqttDeliveryToken publish = mqttTopic.publish(message);
            publish.waitForCompletion();
            logger.info("消息发送成功:" + publish.isComplete());
            return R.ok();
        } catch (MqttException e) {
            e.printStackTrace();
            return R.error();
        }
    }
}
