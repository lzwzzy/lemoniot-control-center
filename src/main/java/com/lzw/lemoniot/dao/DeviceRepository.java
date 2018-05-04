package com.lzw.lemoniot.dao;

import com.lzw.lemoniot.modal.Device;
import com.lzw.lemoniot.modal.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Set;

/**
 * DeviceRepository
 *
 * @author lzw
 * @date 2018/4/9 14:45
 **/
public interface DeviceRepository extends JpaRepository<Device, Long> {

    boolean existsDeviceById(String deviceId);

    boolean existsByUsers(Set<User> users);
}
