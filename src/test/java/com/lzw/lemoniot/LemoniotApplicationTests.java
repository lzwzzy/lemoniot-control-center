package com.lzw.lemoniot;

import com.lzw.lemoniot.dao.DeviceRepository;
import com.lzw.lemoniot.dao.UserRepository;
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

    @Test
    public void contextLoads() {
//        Device device = new Device();
//        device.setId(1);
//        device.setGetway(true);
//        device.setName("gateway");
//        device.setType("GateWay");
//        User user = new User();
//        user.setId(1);
//        user.setName("张三");
//        Set<Device> devices = new HashSet<>();
//        devices.add(device);
//        user.setDevices(devices);
//        userRepository.saveAndFlush(user);
//
//        userRepository.findById(1);
    }

}
