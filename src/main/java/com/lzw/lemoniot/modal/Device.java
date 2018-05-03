package com.lzw.lemoniot.modal;

import javax.persistence.*;
import java.util.Objects;
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
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private String name;

    private String type;

    private boolean isGetway;

    @ManyToMany(cascade = CascadeType.ALL)
    private Set<User> users;

    @ManyToOne(cascade = CascadeType.ALL)
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

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Device device = (Device) o;
        return isGetway == device.isGetway &&
                Objects.equals(id, device.id) &&
                Objects.equals(name, device.name) &&
                Objects.equals(type, device.type);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, name, type, isGetway);
    }
}
