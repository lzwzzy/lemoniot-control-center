package com.lzw.lemoniot.service;

import com.lzw.lemoniot.modal.User;

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
    void bindDevice(User user);
}
