package com.base.services;

import com.base.entitys.Device;
import com.base.entitys.DeviceType;
import com.base.entitys.DeviceTypePort;
import com.base.repositorys.DeviceDao;
import com.base.repositorys.DeviceTypeDao;
import com.base.repositorys.DeviceTypePortDao;
import com.base.repositorys.QueryDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by lx on 2016/11/25.
 */
@Service
public class DeviceService {
     @Autowired
     DeviceDao deviceDao;
     @Autowired
     DeviceTypeDao deviceTypeDao;
     @Autowired
     QueryDao queryDao;

    public Device saveDevice(String deviceID,String chinatypename){
        Device device=new Device();
        device.setDeviceID(deviceID);
        DeviceType deviceType=this.deviceTypeDao.findByChinatypename(chinatypename);
        device.setDeviceType(deviceType);
        return  deviceDao.save(device);
    }


    private String sql(String info){
        String sql="select dt.id devicetypeid,d.deviceid,dt.chinatypename,dt.hextypename,dt.typename,dt.typenum from tb_device d left outer join tb_devicetype dt on d.devicetype_fk = dt.id where dt.chinatypename like "+info+" or d.deviceid like "+info+" or dt.hextypename like "+info+" or dt.typename like "+info+" or dt.typenum like "+info;
        return sql;
    }

    public Page<Map<String,Object>> deviceSqlSearch(Integer page, Integer size, String info){
        Sort sort = new Sort(Sort.Direction.ASC, "d.id");
        Pageable pageable = new PageRequest(page-1, size, sort);
        Page<Map<String,Object>>  page2=this.queryDao.query(this.sql(info),pageable);
        return page2;
}
        }
