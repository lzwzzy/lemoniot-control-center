package com.lzw.lemoniot.dao;

import com.lzw.lemoniot.modal.Room;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * RoomRepository
 *
 * @author lzw
 * @date 2018/5/9 22:08
 **/
public interface RoomRepository extends JpaRepository<Room, String> {

}
