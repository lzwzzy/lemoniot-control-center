package com.lzw.lemoniot.service;

import com.lzw.lemoniot.modal.Device;
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
     * 绑定设备
     */
    User bindDevice(User user);

    /**
     * 解绑设备
     * @param user
     */
    void unbindDevice(User user, String deviceId);

    /**
     * 获取该用户下所有设备
     * @param userId
     * @return
     */
    List<Device> getDevices(String userId);


    boolean deviceIsExists(String wxDeviceId);


}
