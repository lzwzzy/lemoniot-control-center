package com.lzw.lemoniot.service;

import com.lzw.lemoniot.modal.User;

/**
 * UserService
 *
 * @author lzw
 * @date 2018/4/9 21:58
 **/
public interface UserService {

    /**
     * 保存用户
     * @param user user
     * @return user
     */
    User saveUser(User user);

    /**
     * 根据openId（微信）查找用户
     * @param openId 关注公众号的用户唯一值
     * @return user
     */
    User findUserByOpenId(String openId);
}
