package com.lzw.lemoniot.modal;

import javax.persistence.*;
import java.util.Objects;
import java.util.Set;

/**
 * Room
 *
 * @author lzw
 * @date 2018/4/9 12:07
 **/
@Entity
public class Room {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private String name;

    private String type;

    private String remarks;

    @OneToMany(cascade = CascadeType.ALL)
    private Set<Device> devices;



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

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public Set<Device> getDevices() {
        return devices;
    }

    public void setDevices(Set<Device> devices) {
        this.devices = devices;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Room)) {
            return false;
        }
        Room room = (Room) o;
        return Objects.equals(id, room.id) &&
                Objects.equals(name, room.name) &&
                Objects.equals(type, room.type) &&
                Objects.equals(remarks, room.remarks) &&
                Objects.equals(devices, room.devices);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, name, type, remarks, devices);
    }
}
