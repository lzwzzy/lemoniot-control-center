package com.lzw.lemoniot;

import com.lzw.lemoniot.dao.DeviceRepository;
import com.lzw.lemoniot.dao.UserRepository;
import com.lzw.lemoniot.modal.Device;
import org.eclipse.paho.client.mqttv3.MqttException;
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
        Device device = new Device();
        device.setId("adfasdfasdf");
        deviceRepository.save(device);
    }

}
