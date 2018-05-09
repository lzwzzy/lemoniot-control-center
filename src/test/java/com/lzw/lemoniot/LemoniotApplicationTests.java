package com.lzw.lemoniot;

import com.lzw.lemoniot.dao.DeviceRepository;
import com.lzw.lemoniot.dao.UserRepository;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.eclipse.paho.client.mqttv3.MqttTopic;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class LemoniotApplicationTests {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private DeviceRepository deviceRepository;

    @Autowired
    private Mqtt mqtt;

    @Test
    public void contextLoads() throws MqttException {
        MqttClient client = mqtt.getClient();
        MqttTopic mqttTopic = client.getTopic("/device/event");
        MqttMessage mqttMessage = new MqttMessage();
        mqttMessage.setQos(1);
        mqttMessage.setPayload("ok".getBytes());
        mqttMessage.setRetained(true);
        mqttTopic.publish(mqttMessage);
        client.subscribe("/device/event", 1);
    }

}
