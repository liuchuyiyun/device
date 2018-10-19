package com.base.controller;

import com.base.entitys.Device;
import com.base.entitys.DeviceType;
import com.base.entitys.DeviceTypePort;
import com.base.entitys.Gate;
import com.base.repositorys.DeviceDao;
import com.base.repositorys.DeviceTypePortDao;
import com.base.services.DeviceService;
import com.base.services.DeviceTypePortService;
import com.base.services.DeviceTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by lx on 2016/11/25.
 */
@Controller
@CrossOrigin(origins = {}, methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.OPTIONS})
public class ProController {
    @Autowired
    DeviceService deviceService;
    @Autowired
    DeviceTypeService deviceTypeService;
    @Autowired
    DeviceTypePortService deviceTypePortService;
    @Autowired
    DeviceDao deviceDao;
    @Autowired
    DeviceTypePortDao deviceTypePortDao;


    @RequestMapping("/devicetypeadd")
    @ResponseBody
    Object devicetypeadd(@RequestBody DeviceType deviceType){
            Object result1=this.deviceTypeService.saveDeviceType(deviceType);
            Map map = new HashMap();
            if (result1==null) {
            map.put("code", "0");
            map.put("msg", "fail");
            } else {
            map.put("code", "1");
            map.put("msg", "success");
           }
           return map;
    }

    @RequestMapping("/deviceadd")
    @ResponseBody
    Object deviceadd(String deviceID ,String chinatypename){
        Object result1=this.deviceService.saveDevice(deviceID,chinatypename);
        Map map = new HashMap();
        if (result1 == null) {
            map.put("code", "0");
            map.put("msg", "fail");
        } else {
            map.put("code", "1");
            map.put("msg", "success");
        }
        return map;
    }

    @RequestMapping("/devicetypeportadd")
    @ResponseBody
    Object devicetypeportadd(String port,String portmean,String portalarm,String message,long id){
        Object result1=this.deviceTypePortService.saveDeviceTypePort(port,portmean,portalarm,message,id);
        Map map = new HashMap();
        if (result1 == null) {
            map.put("code", "0");
            map.put("msg", "fail");
        } else {
            map.put("code", "1");
            map.put("msg", "success");
        }
        return map;
    }

    @RequestMapping("/selectdeviceport")
    @ResponseBody
    Object selectdeviceport(){
        return this.deviceTypePortService.findAll();
    }

    @RequestMapping("/deviceport")
    @ResponseBody
    Object deviceport(Long devicetypeid){
        return this.deviceTypePortService.devicePort(devicetypeid);
    }

    @RequestMapping(value = "/deviceparams")
    @ResponseBody
    public Object getEntryByParams(@RequestParam(value = "page", defaultValue = "1") Integer page,
                                         @RequestParam(value = "size", defaultValue = "8") Integer size) {
        Sort sort = new Sort(Sort.Direction.ASC, "id");
        Pageable pageable = new PageRequest(page-1, size, sort);
        Page<Device> Exams = deviceDao.findAll(pageable);
        Map map=new HashMap();
        map.put("Total",Exams.getTotalElements());
        map.put("Content",Exams.getContent());
        return map;
    }

    @RequestMapping(value = "/devicesearch")
    @ResponseBody
    public Object getEntryBySearch(@RequestParam(value = "page", defaultValue = "1") Integer page,
                                   String info,
                                   @RequestParam(value = "size", defaultValue = "8") Integer size) {
        String info2="'%"+info+"%'";
        Page<Map<String,Object>> Exams = deviceService.deviceSqlSearch(page,size,info2);
        Map map=new HashMap();
        map.put("Total",Exams.getTotalElements());
        map.put("Content",Exams.getContent());
        return map;
    }

    @RequestMapping(value = "/deviceportparams")
    @ResponseBody
    public Object getEntryByParams1(@RequestParam(value = "page", defaultValue = "1") Integer page,
                                   @RequestParam(value = "size", defaultValue = "8") Integer size) {
        Sort sort = new Sort(Sort.Direction.ASC, "id");
        Pageable pageable = new PageRequest(page-1, size, sort);
        Page<DeviceTypePort> Exams = deviceTypePortDao.findAll(pageable);
        Map map=new HashMap();
        map.put("Total",Exams.getTotalElements());
        map.put("Content",Exams.getContent());
        return map;
    }


    @RequestMapping(value = "/deviceportsearch")
    @ResponseBody
    public Object getEntryBySearch1(@RequestParam(value = "page", defaultValue = "1") Integer page,
                                   String info,
                                   @RequestParam(value = "size", defaultValue = "8") Integer size) {
        String info2="'%"+info+"%'";
        Page<Map<String,Object>> Exams = deviceTypePortService.deviceportSqlSearch(page,size,info2);
        Map map=new HashMap();
        map.put("Total",Exams.getTotalElements());
        map.put("Content",Exams.getContent());
        return map;
    }
}
