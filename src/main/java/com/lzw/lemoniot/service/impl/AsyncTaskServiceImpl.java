package com.lzw.lemoniot.service.impl;

import com.lzw.lemoniot.dao.RoomRepository;
import com.lzw.lemoniot.dao.UserRepository;
import com.lzw.lemoniot.modal.Room;
import com.lzw.lemoniot.modal.User;
import com.lzw.lemoniot.service.AsyncTaskService;
import com.lzw.lemoniot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

/**
 * AsyncTaskServiceImpl
 *
 * @author lzw
 * @date 2018/4/9 22:46
 **/
@Service
public class AsyncTaskServiceImpl implements AsyncTaskService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoomRepository roomRepository;

    @Override
    @Async
    public void asyncSaveUser(User user) {
        if (userRepository.findByOpenId(user.getOpenId()) == null){
            //判断当前用户是否有房间，如果没有创建默认房间
            if (!roomRepository.existsRoomByUser(user)){
                Room room = new Room();
                room.setName("默认房间");
                room.setRemarks("这是默认房间");
                room.setType("默认类型");
                room.setUser(user);
                Set<Room> rooms = new HashSet<>();
                user.setRooms(rooms);
            }
            userRepository.save(user);
        }
    }
}
