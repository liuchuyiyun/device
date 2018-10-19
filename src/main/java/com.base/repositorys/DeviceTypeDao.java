package com.base.repositorys;

import com.base.entitys.DeviceType;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by lx on 2016/12/2.
 */
@Repository
public interface DeviceTypeDao extends CrudRepository<DeviceType,Long> {

    DeviceType findByChinatypename(String chinatypename);

//    @Query(value="select * from tb_devicetype where chinatypename like ?1 or hextypename like ?1 or typename like ?1 or typenum like ?1",nativeQuery=true)
//    DeviceType findSearch(String info);

}
