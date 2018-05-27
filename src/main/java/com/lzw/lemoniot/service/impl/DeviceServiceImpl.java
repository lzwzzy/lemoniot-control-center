package com.lzw.lemoniot.service.impl;

import com.lzw.lemoniot.dao.DeviceRepository;
import com.lzw.lemoniot.dao.RoomRepository;
import com.lzw.lemoniot.dao.UserRepository;
import com.lzw.lemoniot.e.ErrorEnum;
import com.lzw.lemoniot.exception.LemonException;
import com.lzw.lemoniot.modal.Device;
import com.lzw.lemoniot.modal.Room;
import com.lzw.lemoniot.modal.User;
import com.lzw.lemoniot.service.DeviceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

/**
 * DeviceServiceImpl
 *
 * @author lzw
 * @date 2018/5/3 17:39
 **/
@Service
public class DeviceServiceImpl implements DeviceService {

    @Autowired
    DeviceRepository deviceRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    RoomRepository roomRepository;

    /**
     * 绑定设备
     *
     * @param user user 用户
     *             device 设备
     */
    @Override
    public Device bindDeviceToUser(Device device, User user) {
        try {
            device.setUser(user);
            return deviceRepository.saveAndFlush(device);
        } catch (Exception e) {
            throw new LemonException(ErrorEnum.System.SYSTEM_ERROR, e);
        }
    }

    /**
     * 解绑设备
     *
     * @param user
     */
    @Override
    public void unbindDeviceFromUser(User user, Device device) {
        user.getDevices().remove(device);
        userRepository.saveAndFlush(user);
    }

    /**
     * 绑定设备到房间
     *
     * @param
     */
    @Override
    public Device bindDeviceToRoom(Device device, Room room) {
        device.setRoom(room);
        return deviceRepository.saveAndFlush(device);
    }

    /**
     * 解绑设备从房间
     *
     * @param room 房间
     * @param device 设备
     */
    @Override
    public void unbindDeviceFromRoom(Room room, Device device) {
        room.getDevices().remove(device);
        roomRepository.saveAndFlush(room);
    }

    /**
     * 获取该用户下所有设备
     *
     * @param userId 用户id
     * @return
     */
    @Override
    public List<Device> getDevices(Long userId) {
        User userDevices = userRepository.findByUserIdOrderByUserIdAsc(userId);
        if (userDevices != null && userId !=null){
            Set<Device> devices = userDevices.getDevices();
            if (devices != null) {
                return new ArrayList<>(devices);
            }
        }
        return null;
    }

    /**
     * 获取该用户下所有房间
     *
     * @param userId
     * @return
     */
    @Override
    public List<Room> getRooms(Long userId) {
        User userRooms = userRepository.findByUserIdOrderByUserIdAsc(userId);
        if (userRooms != null && userId !=null){
            Set<Room> rooms = userRooms.getRooms();
            if (rooms != null) {
                return new ArrayList<>(rooms);
            }
        }
        return null;
    }

    @Override
    public boolean deviceIsExists(String wxDeviceId) {
        return deviceRepository.existsByWechatDeviceId(wxDeviceId);
    }
}
