package com.base.services;

import com.base.entitys.Gate;
import com.base.repositorys.GateDao;
import com.base.repositorys.QueryDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by lx on 2016/11/30.
 */
@Service
public class GateService {
    @Autowired
    private GateDao gateDao;

    @Autowired
    QueryDao queryDao;

   public Object findone(Long id){
        return gateDao.findOne(id);
    }

    public Object findall(){
        return gateDao.findAll();
    }


    public int isExist(String gateID,String name,String pass){
        List<Gate> result = gateDao.findByGateID(gateID);
        List<Gate> result2 = gateDao.findByConnectnameAndConnnectpass(name,pass);
        if(result.size()==0){
            return 1;
        }else if(result2.size()==0)
            return 2;
        return 0;
    }

    public Map mapIsExist(String gateID,String name,String pass ){
        int result=isExist(gateID, name, pass);
        Map map=new HashMap();
        if(result==1) {
            map.put("code","3");
            map.put("msg","网关id出错！");
        }
        if(result==2) {
            map.put("code","2");
            map.put("msg","用户名或密码错误！");
        }
        if (result==0) {
            map.put("code","1");
            map.put("msg","success");
        }
        return map;
    }

    public int updateGate(String name,String pass,String newpass)
    {
        List<Gate> result = gateDao.findByConnectnameAndConnnectpass(name, pass);
        if(result.size()!=0){
            for(Gate gate:result)
            {
                gate.setConnnectpass(newpass);
                gateDao.save(gate);
                break;
            }
            return 1;
        }else{
            return 0;
        }
    }

    private String sql(String info){
        String sql="select * from tb_gate where gateid like "+info+" or connectname like"+info+" or connectpass like"+info+" or inserttime like"+info;
        return sql;
    }

    public Object sqlSearch(String info){
        Object result=this.queryDao.queryfind(this.sql(info));
        return result;
    }


    public void images(String name,String image){
        List<Gate> gate= this.gateDao.findByConnectname(name);
        gate.get(0).setBg(image);
        this.gateDao.save(gate);
    }


    public Object getimages(String name){
        List<Gate> gate=this.gateDao.findByConnectname(name);
        return gate.get(0).getBg();
    }

}
