package com.lzw.lemoniot.service.impl;

import com.lzw.lemoniot.dao.DeviceRepository;
import com.lzw.lemoniot.dao.UserRepository;
import com.lzw.lemoniot.e.ErrorEnum;
import com.lzw.lemoniot.exception.LemonException;
import com.lzw.lemoniot.modal.Device;
import com.lzw.lemoniot.modal.User;
import com.lzw.lemoniot.service.DeviceService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
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

    /**
     * 绑定设备
     *
     * @param user user 用户
     *             device 设备
     */
    @Override
    public User bindDevice(User user) {
        try {
            return userRepository.save(user);
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
    public void unbindDevice(User user, String deviceId) {
        user.getDevices().remove(deviceId);
        userRepository.saveAndFlush(user);
    }

    /**
     * 获取该用户下所有设备
     *
     * @param userId 用户id
     * @return
     */
    @Override
    public List<Device> getDevices(String userId) {
        User user_devices = userRepository.findById(userId);
        if (user_devices != null && !StringUtils.isBlank(userId)){
            Set<Device> devices = user_devices.getDevices();
            if (devices != null) {
                return new ArrayList<>(devices);
            }
        }
        return null;
    }

    @Override
    public boolean deviceIsExists(String wxDeviceId) {
        return deviceRepository.existsByWechatDeviceId(wxDeviceId);
    }
}
