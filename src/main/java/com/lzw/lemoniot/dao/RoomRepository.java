package com.lzw.lemoniot.dao;

import com.lzw.lemoniot.modal.Room;
import com.lzw.lemoniot.modal.User;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.web.SortDefault;

/**
 * RoomRepository
 *
 * @author lzw
 * @date 2018/5/9 22:08
 **/
public interface RoomRepository extends JpaRepository<Room, Long> {

    boolean existsRoomByUser(User user);

    Room findByNameOrderByRoomIdAsc(String name);

    Room findByUserAndType(User user, String type);


}
