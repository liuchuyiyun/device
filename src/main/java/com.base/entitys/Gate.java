package com.base.entitys;

import javax.persistence.*;

/**
 * Created by lx on 2016/12/1.
 */
@Entity
@Table(name="tb_gate")
public class Gate {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String gateID;
    private String connectname;
    private String connnectpass;
    private Long inserttime;
    private String bg;

    public String getBg() {
        return bg;
    }

    public void setBg(String bg) {
        this.bg = bg;
    }

    public Long getInserttime() {
        return inserttime;
    }

    public void setInserttime(Long inserttime) {
        this.inserttime = inserttime;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getGateID() {
        return gateID;
    }

    public void setGateID(String gateID) {
        this.gateID = gateID;
    }

    public String getConnnectpass() {
        return connnectpass;
    }

    public void setConnnectpass(String connnectpass) {
        this.connnectpass = connnectpass;
    }

    public String getConnectname() {
        return connectname;
    }

    public void setConnectname(String connectname) {
        this.connectname = connectname;
    }
}
