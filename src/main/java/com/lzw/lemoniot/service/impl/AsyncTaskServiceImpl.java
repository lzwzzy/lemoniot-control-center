package com.lzw.lemoniot.service.impl;

import com.lzw.lemoniot.dao.UserRepository;
import com.lzw.lemoniot.modal.User;
import com.lzw.lemoniot.service.AsyncTaskService;
import com.lzw.lemoniot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

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

    @Override
    @Async
    public void asyncSaveUser(User user) {
        if (userRepository.findByOpenId(user.getOpenId()) == null){
            userRepository.save(user);
        }
    }
}
