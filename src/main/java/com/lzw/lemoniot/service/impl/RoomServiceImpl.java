package com.lzw.lemoniot.service.impl;

import com.lzw.lemoniot.dao.RoomRepository;
import com.lzw.lemoniot.dao.UserRepository;
import com.lzw.lemoniot.modal.Room;
import com.lzw.lemoniot.modal.User;
import com.lzw.lemoniot.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

/**
 * RoomServiceImpl
 *
 * @author lzw
 * @date 2018/5/9 22:12
 **/
@Service
public class RoomServiceImpl implements RoomService {

    @Autowired
    private RoomRepository roomRepository;

    @Autowired
    private UserRepository userRepository;

    /**
     * 获取所有房间
     *
     * @param userId
     * @return
     */
    @Override
    public List<Room> getRooms(Long userId) {
        User user_rooms = userRepository.findByUserId(userId);

        Set<Room> rooms = user_rooms.getRooms();
        if (rooms != null) {
            return new ArrayList<>(rooms);
        }
        return null;
    }
}
