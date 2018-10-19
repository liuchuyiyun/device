package com.base.services;

import com.base.entitys.DeviceType;
import com.base.entitys.DeviceTypePort;
import com.base.repositorys.DeviceTypeDao;
import com.base.repositorys.DeviceTypePortDao;
import com.base.repositorys.QueryDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * Created by lx on 2016/12/2.
 */
@Service
public class DeviceTypePortService {
    @Autowired
    private DeviceTypePortDao deviceTypePortDao;
    @Autowired
    DeviceTypeDao deviceTypeDao;
    @Autowired
    QueryDao queryDao;

    public DeviceTypePort saveDeviceTypePort(String port,String portmean,String portalarm,String message,Long id){
        DeviceTypePort deviceTypePort=new DeviceTypePort();
        deviceTypePort.setPort(port);
        deviceTypePort.setPortalarm(portalarm);
        deviceTypePort.setPortmean(portmean);
        deviceTypePort.setMessage(message);
        DeviceType deviceType=this.deviceTypeDao.findOne(id);
        deviceTypePort.setDeviceType(deviceType);
        return deviceTypePortDao.save(deviceTypePort);
    }

    public Object findAll(){
        return deviceTypePortDao.findAll();
    }


    public Object devicePort(Long devicetypeid){
        DeviceType deviceType=this.deviceTypeDao.findOne(devicetypeid);
        return this.deviceTypePortDao.findByDeviceType(deviceType);
    }

    private String sql(String info){
        String sql="select d.id,d.port,d.portmean,d.portalarm,d.message from tb_devicetypeport d where port like "+info+" or portmean like "+info+" or portalarm like "+info+" or message like "+info;
        return sql;
    }

    public Page<Map<String,Object>> deviceportSqlSearch(Integer page, Integer size, String info){
        Sort sort = new Sort(Sort.Direction.ASC, "d.id");
        Pageable pageable = new PageRequest(page-1, size, sort);
        Page<Map<String,Object>>  page2=this.queryDao.query(this.sql(info),pageable);
        return page2;
    }
}
