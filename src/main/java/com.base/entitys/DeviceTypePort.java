package com.base.entitys;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

/**
 * Created by lx on 2016/12/1.
 */
@Entity()
@Table(name="tb_devicetypeport")
public class DeviceTypePort {


    private long id;
    private String port;
    private String portmean;
    private String portalarm;
    private String message;
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

    public String getPort() {
        return port;
    }

    public void setPort(String port) {
        this.port = port;
    }

    public String getPortmean() {
        return portmean;
    }

    public void setPortmean(String portmean) {
        this.portmean = portmean;
    }

    public String getPortalarm() {
        return portalarm;
    }

    public void setPortalarm(String portalarm) {
        this.portalarm = portalarm;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
