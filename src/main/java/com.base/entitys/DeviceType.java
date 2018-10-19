package com.base.entitys;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by lx on 2016/12/1.
 */
@Entity()
@Table(name="tb_devicetype")
public class DeviceType {


    private Long id;
    private String chinatypename;
    private String hextypename;
    private String typename;
    private Integer typenum;


//    @JsonIgnore
//    @OneToMany(cascade=CascadeType.ALL,fetch=FetchType.LAZY,mappedBy="devicetypeport")
//    @LazyCollection(LazyCollectionOption.EXTRA)
//    privateSet<DeviceTypePort> deviceTypePortsitems=new HashSet<DeviceTypePort>();


    public String getTypename() {
        return typename;
    }

    public void setTypename(String typename) {
        this.typename = typename;
    }

    public String getHextypename() {
        return hextypename;
    }

    public void setHextypename(String hextypename) {
        this.hextypename = hextypename;
    }

    public Integer getTypenum() {
        return typenum;
    }

    public void setTypenum(Integer typenum) {
        this.typenum = typenum;
    }

    public String getChinatypename() {
        return chinatypename;
    }

    public void setChinatypename(String chinatypename) {
        this.chinatypename = chinatypename;
    }


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
