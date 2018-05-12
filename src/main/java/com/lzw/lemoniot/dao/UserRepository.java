package com.lzw.lemoniot.dao;

import com.lzw.lemoniot.modal.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * @author lzw
 * @date 2018/4/9 0:47
 **/
public interface UserRepository extends JpaRepository<User, Long> {


    boolean existsByOpenId(String openId);
    

    User findByUserId(Long id);


    User findByOpenId(String openId);

}
