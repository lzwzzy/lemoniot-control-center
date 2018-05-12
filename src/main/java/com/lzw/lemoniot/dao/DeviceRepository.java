package com.lzw.lemoniot.dao;

import com.lzw.lemoniot.modal.Device;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * DeviceRepository
 *
 * @author lzw
 * @date 2018/4/9 14:45
 **/
public interface DeviceRepository extends JpaRepository<Device, Long> {
    boolean existsByWechatDeviceId(String wechatDeviceId);

    Device findByWechatDeviceId(String wechatDeviceId);
}
