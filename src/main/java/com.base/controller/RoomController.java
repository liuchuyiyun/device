package com.base.controller;

import com.base.entitys.Room;
import com.base.repositorys.RoomDao;
import com.base.services.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by lx on 2016/12/1.
 */
@Controller
@CrossOrigin(origins = {}, methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.OPTIONS})
public class RoomController {

    @Autowired
    RoomService roomService;

    @Autowired
    RoomDao roomDao;

    @RequestMapping("/insertroom")
    @ResponseBody
    Object insertroom(String roomtypename){
        Room room=new Room();
        room.setRoomtypename(roomtypename);
        Object result=this.roomService.saveRoom(room);
        Map map = new HashMap();
        if (result == null) {
            map.put("code", "0");
            map.put("msg", "添加房间失败");
        } else {
            map.put("code", "1");
            map.put("msg", "添加房间成功");
        }
        return map;
    }

    @RequestMapping("/queryroom")
    @ResponseBody
    Object queryroom(){
        return this.roomService.findall();
    }


    @RequestMapping("/deleteroom")
    @ResponseBody
    Object deleteroom(long id){
      Object result=this.roomService.deleteRoom(id);
        Map map = new HashMap();
        if (result == "fail") {
            map.put("code", "0");
            map.put("msg", "删除房间失败");
        } else {
            map.put("code", "1");
            map.put("msg", "删除房间成功");
        }
        return map;
    }

    @RequestMapping("/updateroom")
    @ResponseBody
    Object updateroom(long id,String roomtypename,String newroomtypename){
       int result=this.roomService.updateRoom(id, roomtypename, newroomtypename);
        Map map=new HashMap();
        if(result==1) {
            map.put("code", "1");
            map.put("msg", "房间类型修改成功");
        }
        if(result==0){
            map.put("code", "0");
            map.put("msg", "房间类型修改失败");
        }
        return map;
    }

    @RequestMapping(value = "/roomparams")
    @ResponseBody
    public Object getEntryByParams(@RequestParam(value = "page", defaultValue = "1") Integer page,
                                   @RequestParam(value = "size", defaultValue = "8") Integer size) {
        Sort sort = new Sort(Sort.Direction.ASC, "id");
        Pageable pageable = new PageRequest(page-1, size, sort);
        Page<Room> Exams = roomDao.findAll(pageable);
        Map map=new HashMap();
        map.put("Total",Exams.getTotalElements());
        map.put("Content",Exams.getContent());
        return map;
    }

    @RequestMapping(value = "/roomsearch")
    @ResponseBody
    public Object getEntryBySearch(@RequestParam(value = "page", defaultValue = "1") Integer page,
                                   String info,
                                   @RequestParam(value = "size", defaultValue = "8") Integer size) {
        String info2="'%"+info+"%'";
        Page<Map<String,Object>> Exams = roomService.sqlSearch(page,size,info2);
        Map map=new HashMap();
        map.put("Total",Exams.getTotalElements());
        map.put("Content",Exams.getContent());
        return map;
    }

}
