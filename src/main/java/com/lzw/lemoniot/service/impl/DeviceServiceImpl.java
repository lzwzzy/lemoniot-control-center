package com.lzw.lemoniot.service.impl;

import com.lzw.lemoniot.dao.DeviceRepository;
import com.lzw.lemoniot.dao.UserRepository;
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
     * @param user
     */
    @Override
    public void bindDevice(User user) {
        User save = userRepository.save(user);
    }
}
