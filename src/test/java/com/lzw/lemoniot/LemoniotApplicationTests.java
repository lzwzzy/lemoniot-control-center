package com.lzw.lemoniot;

import com.lzw.lemoniot.dao.DeviceRepository;
import com.lzw.lemoniot.dao.RoomRepository;
import com.lzw.lemoniot.dao.UserRepository;
import com.lzw.lemoniot.modal.Device;
import com.lzw.lemoniot.modal.Room;
import com.lzw.lemoniot.modal.User;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Optional;

import static org.xmlunit.diff.ElementSelectors.byName;

@RunWith(SpringRunner.class)
@SpringBootTest
public class LemoniotApplicationTests {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private DeviceRepository deviceRepository;

    @Autowired
    private RoomRepository roomRepository;

    @Autowired
    private Mqtt mqtt;

    @Test
    public void contextLoads() throws MqttException {
//        User byId = userRepository.findByUserId(Long.parseLong("1"));
//        System.out.println("byId = " + byId);
//        User user1 = new User();
//        user1.setName("user1");
//        User user2 = new User();
//        user2.setName("user2");
//        user1 = userRepository.save(user1);
//        user2 = userRepository.save(user2);
//
//
//        Room room1= new Room();
//        room1.setName("room1");
//        room1.setUser(user1);
//        Room room2 = new Room();
//        room2.setName("room2");
//        room2.setUser(user2);
//        roomRepository.saveAndFlush(room1);
//        roomRepository.saveAndFlush(room2);

//        List<Room> all = roomRepository.findAll();
////        System.out.println("byId = " + all);
        Optional<Device> device = deviceRepository.findById(11L);
        Optional<Room> room = roomRepository.findById(10L);
        device.get().setRoom(room.get());
        deviceRepository.saveAndFlush(device.get());
    }

}
