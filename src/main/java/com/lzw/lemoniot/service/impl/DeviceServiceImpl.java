package com.lzw.lemoniot.service.impl;

import com.lzw.lemoniot.dao.DeviceRepository;
import com.lzw.lemoniot.dao.UserRepository;
import com.lzw.lemoniot.e.ErrorEnum;
import com.lzw.lemoniot.exception.LemonException;
import com.lzw.lemoniot.modal.User;
import com.lzw.lemoniot.service.DeviceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    public void unbindDevice(User user) {
        userRepository.delete(user);
    }
}
