package com.lzw.lemoniot.modal;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Set;

/**
 * Device
 *
 * @author lzw
 * @date 2018/4/9 12:05
 **/
@Entity
public class Device {
    @Id
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    @GeneratedValue(generator = "system-uuid")
    private String id;

    private String name;

    private String type;

    private boolean isGetway;

    @Column(unique = true)
    private String wechatDeviceId;

    @ManyToMany(cascade = {CascadeType.MERGE, CascadeType.REFRESH, CascadeType.PERSIST}, fetch = FetchType.EAGER)
    private Set<User> users;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH},fetch = FetchType.EAGER)
    private Room room;


    public Device() {
    }




    public String getWechatDeviceId() {
        return wechatDeviceId;
    }

    public void setWechatDeviceId(String wechatDeviceId) {
        this.wechatDeviceId = wechatDeviceId;
    }

    public Set<User> getUsers() {
        return users;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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
