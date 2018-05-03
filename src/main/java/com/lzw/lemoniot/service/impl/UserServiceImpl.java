package com.lzw.lemoniot.service.impl;

import com.lzw.lemoniot.dao.UserRepository;
import com.lzw.lemoniot.exception.LemonException;
import com.lzw.lemoniot.modal.User;
import com.lzw.lemoniot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * UserServiceImpl
 *
 * @author lzw
 * @date 2018/4/9 21:59
 **/
@Service
public class UserServiceImpl implements UserService {
    
    @Autowired
    private UserRepository userRepository;

    /**
     * 保存用户
     *
     * @param user
     * @return
     */
    @Override
    @Transactional(rollbackFor = LemonException.class)
    public void saveUser(User user) {
        userRepository.save(user);
    }

    /**
     * 根据openId（微信）查找用户
     *
     * @param openId 关注公众号的用户唯一值
     * @return user
     */
    @Override
    public User findUserByOpenId(String openId) {
        return userRepository.findByOpenId(openId);
    }
}
