package com.base.services;

import com.base.entitys.Room;
import com.base.repositorys.QueryDao;
import com.base.repositorys.RoomDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.criteria.*;
import java.util.List;
import java.util.Map;

/**
 * Created by lx on 2016/12/1.
 */
@Service
public class RoomService {
    @Autowired
    private RoomDao roomDao;

    @Autowired
    QueryDao queryDao;

    public Room saveRoom(Room room){ return roomDao.save(room); }

    public Object findall(){return roomDao.findAll();}

    @Transactional
    public String deleteRoom(Long id){
        List<Room> roomlist=roomDao.findById(id);
        if(roomlist.size()!=0){
            for(Room room1:roomlist){
                roomDao.delete(room1.getId());
                break;
            }
            return "success";
        }else {
            return "fail";
        }
    }

    public int updateRoom(long id,String roomtypename,String newroomtypename){
        List<Room> result=roomDao.findByIdAndRoomtypename(id, roomtypename);
        if(result.size()!=0) {
            for (Room room : result) {
                room.setRoomtypename(newroomtypename);
                roomDao.save(room);
                break;
            }
            return 1;
        } else{
                return 0;
            }
        }

        private String sql(String info){
            String sql="select * from tb_room where roomtypename like "+info+" or id like"+info;
        return sql;
        }

    public Page<Map<String,Object>> sqlSearch(Integer page,Integer size,String info){
        Sort sort = new Sort(Sort.Direction.ASC, "id");
        Pageable pageable = new PageRequest(page-1, size, sort);
        Page<Map<String,Object>>  page2=this.queryDao.query(this.sql(info),pageable);
        return page2;
    }

}
