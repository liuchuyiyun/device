package com.base.controller;

import com.base.entitys.Gate;
import com.base.repositorys.GateDao;
import com.base.services.GateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by lx on 2016/12/1.
 */
@Controller
@CrossOrigin(origins = {}, methods = {RequestMethod.GET,RequestMethod.POST, RequestMethod.OPTIONS})
public class GateController {


    @RequestMapping("/")
    ModelAndView f(){
        return new ModelAndView("login.html");
    }

    @Autowired
    GateService gateService;
    @Autowired
    GateDao gateDao;

    @RequestMapping("/selectByGateIDAndConnectnameAndConnectpass")
    @ResponseBody
    Object home1(String gateID,String name,String pass){
        return this.gateService.mapIsExist(gateID,name,pass);
    }

    @RequestMapping("/updategate")
    @ResponseBody
    Object home3(String name,String pass,String newpass){
        int result=this.gateService.updateGate(name,pass,newpass);
        Map map=new HashMap();
        if(result==1) {
            map.put("code", "1");
            map.put("msg", "密码修改成功");
            }
        if(result==0){
            map.put("code", "0");
            map.put("msg", "密码修改失败");
            }
        return map;
    }

    @RequestMapping("/querygate")
    @ResponseBody
    Object home2(){
        return this.gateService.findall();
    }


    @RequestMapping(value = "/gateparams")
    public Page<Gate> getEntryByParams(@RequestParam(value = "page", defaultValue = "1") Integer page,
                                       @RequestParam(value = "size", defaultValue = "8") Integer size) {
        Sort sort = new Sort(Sort.Direction.ASC, "Id");
        Pageable pageable = new PageRequest(page, size, sort);
        return gateDao.findAll(pageable);
    }

    @RequestMapping("/gatesearch")
        @ResponseBody
    public Object getEntryBySearch(String info){
        String info2="'%"+info+"%'";
       return this.gateService.sqlSearch(info2);
    }



        @RequestMapping("/gatessaveimage")
        @ResponseBody
        public Object saveimageinfo(String name,String image){
            gateService.images(name,image);
            return true;
        }

    @RequestMapping("/gategetimage")
    @ResponseBody
    public Object getimageinfo(String name){
        return this.gateService.getimages(name);
    }
}
