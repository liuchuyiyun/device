package com.base.repositorys;

import com.base.entitys.Room;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by lx on 2016/12/1.
 */
@Repository
public interface RoomDao extends CrudRepository<Room, Long>,JpaSpecificationExecutor<Room> {

    List<Room> findById(long id);
    List<Room> findByIdAndRoomtypename(long id,String roomtypename);

    Page<Room> findAll(Pageable pageable);
}
