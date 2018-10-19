package com.base.entitys;

import javax.persistence.*;

/**
 * Created by lx on 2016/11/30.
 */
@Entity
@Table(name="tb_room")
public class Room {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long id;
    private String roomtypename;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRoomtypename() {
        return roomtypename;
    }

    public void setRoomtypename(String roomtypename) {
        this.roomtypename = roomtypename;
    }
}
