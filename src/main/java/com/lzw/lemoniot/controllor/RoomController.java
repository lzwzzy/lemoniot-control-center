package com.lzw.lemoniot.controllor;

import com.lzw.lemoniot.common.utils.R;
import com.lzw.lemoniot.dao.DeviceRepository;
import com.lzw.lemoniot.dao.RoomRepository;
import com.lzw.lemoniot.modal.Device;
import com.lzw.lemoniot.modal.Room;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.thymeleaf.util.ListUtils;
import org.thymeleaf.util.SetUtils;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * RoomController
 *
 * @author lzw
 * @date 2018/5/12 15:28
 **/
@RestController
@CrossOrigin
@RequestMapping(value = "/rooms")
public class RoomController {

    @Autowired
    private RoomRepository roomRepository;

    @Autowired
    private DeviceRepository deviceRepository;


    @GetMapping(value = "/{roomId}/devices")
    public R getDevices(@PathVariable String roomId) {
        Room one = roomRepository.getOne(Long.parseLong(roomId));
        Set<Device> devices = one.getDevices();
        return R.ok().put("devices",devices);
    }

    @GetMapping(value = "/{roomId}")
    public R getRoom(@PathVariable String roomId) {
        Optional<Room> byId = roomRepository.findById(Long.parseLong(roomId));
        boolean present = byId.isPresent();
        if (present) {
            return R.ok().put("room", byId.get());
        } else {
            return R.error("未找到");
        }
    }

    @PostMapping(value = "/addRoom")
    public R addRoom(@RequestBody Room room){
        room = roomRepository.saveAndFlush(room);
        return R.ok().put("room", room);
    }

    /**
     * 批量绑定设备到房间
     *
     * @param roomId
     * @param room
     * @return
     */
    @PostMapping(value = "/{roomId}/save")
    public R saveRoom(@PathVariable String roomId,
                      @RequestBody Room room) {
        if (!SetUtils.isEmpty(room.getDevices())){
            List<Long> deviceIds = room.getDevices().stream().map(device -> device.getDeviceId()).collect(Collectors.toList());
            deviceRepository.updateDevicesRoomByIds(room, deviceIds);
            room.setDevices(null);
            room = roomRepository.saveAndFlush(room);
            return R.ok().put("room",room);
        }
        return R.error();
    }


}
