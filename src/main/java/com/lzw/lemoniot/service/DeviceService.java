package com.lzw.lemoniot.service;

import com.lzw.lemoniot.modal.Device;
import com.lzw.lemoniot.modal.Room;
import com.lzw.lemoniot.modal.User;

import java.util.List;

/**
 * DeviceService
 *
 * @author lzw
 * @date 2018/5/3 17:38
 **/
public interface DeviceService {
    /**
     * 绑定设备到用户
     */
    Device bindDeviceToUser(Device device, User user);

    /**
     * 解绑设备从用户
     *
     * @param user
     */
    void unbindDeviceFromUser(User user, Device device);

    /**
     * 绑定设备到房间
     */
    Device bindDeviceToRoom(Device device, Room room);

    /**
     * 解绑设备从房间
     *
     * @param room
     */
    void unbindDeviceFromRoom(Room room, Device device);

    /**
     * 获取该用户下所有设备
     *
     * @param userId
     * @return
     */
    List<Device> getDevices(Long userId);

    /**
     * 获取该用户下所有房间
     *
     * @param userId
     * @return
     */
    List<Room> getRooms(Long userId);


    boolean deviceIsExists(String wxDeviceId);


}
