package com.lzw.lemoniot.dao;

import com.lzw.lemoniot.modal.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author lzw
 * @date 2018/4/9 0:47
 **/
public interface UserRepository extends JpaRepository<User, Long> {

    
    void findById(Integer id);


    User findByOpenId(String openId);
}
