package com.lzw.lemoniot.modal;

import com.fasterxml.jackson.annotation.JsonBackReference;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Device
 *
 * @author lzw
 * @date 2018/4/9 12:05
 **/
@Entity
public class Device implements Serializable {

    private Long deviceId;

    private String name;

    private String type;

    private boolean isGetway;

    @Column(unique = true)
    private String wechatDeviceId;


    private User user;


    private Room room;


    public Device() {
    }


    public String getWechatDeviceId() {
        return wechatDeviceId;
    }

    public void setWechatDeviceId(String wechatDeviceId) {
        this.wechatDeviceId = wechatDeviceId;
    }

    @JsonBackReference
    @ManyToOne(cascade = {CascadeType.ALL}, fetch = FetchType.EAGER)
    @NotFound(action = NotFoundAction.IGNORE)
    @JoinColumn(name = "user_id")
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }


    @JsonBackReference
    @ManyToOne(cascade = {CascadeType.ALL}, fetch = FetchType.EAGER)
    @JoinColumn(name = "room_id")
    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(Long deviceId) {
        this.deviceId = deviceId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public boolean isGetway() {
        return isGetway;
    }

    public void setGetway(boolean getway) {
        isGetway = getway;
    }


}
