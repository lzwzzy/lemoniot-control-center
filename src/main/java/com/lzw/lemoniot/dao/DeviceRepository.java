package com.lzw.lemoniot.dao;

import com.lzw.lemoniot.modal.Device;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * DeviceRepository
 *
 * @author lzw
 * @date 2018/4/9 14:45
 **/
@Transactional
public interface DeviceRepository extends JpaRepository<Device, Long>, JpaSpecificationExecutor {
    boolean existsByWechatDeviceId(String wechatDeviceId);

    Device findByWechatDeviceId(String wechatDeviceId);

    @Modifying
    @Query("update Device d set d.room = ?1 where d.deviceId in ?2")
    List<Device> updateDevicesRoomByIds(Long roomId, List<Long> deviceIds);
}
