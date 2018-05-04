package com.lzw.lemoniot.modal;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

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

    @ManyToMany(fetch = FetchType.EAGER, mappedBy = "devices")
    @NotFound(action = NotFoundAction.IGNORE)
    private Set<User> users;

    @ManyToOne
    private Room room;


    public Device() {
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
