package com.lzw.lemoniot.modal;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
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
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    @GeneratedValue(generator = "system-uuid")
    private String id;

    private String name;

    private String type;

    private String remarks;

    @OneToMany
    @JoinTable(name = "room_devices", joinColumns = {@JoinColumn(name = "room_id")}, inverseJoinColumns = {@JoinColumn(name = "device_id")})
    private Set<Device> devices;

    public Set<Device> getDevices() {
        return devices;
    }

    public void setDevices(Set<Device> devices) {
        this.devices = devices;
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

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }




}
