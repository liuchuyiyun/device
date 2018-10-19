package com.base.entitys;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import javax.xml.crypto.Data;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by lx on 2016/11/25.
 */
@Entity
@Table(name="tb_device")
public class Device {


    private long id;
    private String deviceID;
    private DeviceType deviceType;


    @ManyToOne()
    @JoinColumn(name="devicetype_fk")
    public DeviceType getDeviceType() {
        return deviceType;
    }

    public void setDeviceType(DeviceType deviceType) {
        this.deviceType = deviceType;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getDeviceID() {
        return deviceID;
    }

    public void setDeviceID(String deviceID) {
        this.deviceID = deviceID;
    }

}
