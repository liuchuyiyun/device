package com.base.repositorys;

import com.base.entitys.DeviceType;
import com.base.entitys.DeviceTypePort;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by lx on 2016/12/2.
 */
@Repository
public interface DeviceTypePortDao extends CrudRepository<DeviceTypePort,Long> {

    Page<DeviceTypePort> findAll(Pageable page);

    List<DeviceTypePort> findByDeviceType(DeviceType deviceType);

}
