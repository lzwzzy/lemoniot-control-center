package com.lzw.lemoniot.service;

import com.lzw.lemoniot.modal.Room;

import java.util.List;

/**
 * RoomService
 *
 * @author lzw
 * @date 2018/5/9 22:10
 **/
public interface RoomService {
    /**
     * 获取所有房间
     * @param userId
     * @return
     */
    List<Room> getRooms(Long userId);

    /**
     * 添加房间
     * @param userId
     * @param room
     * @return
     */
    Room addRoom(Long userId, Room room);

}
