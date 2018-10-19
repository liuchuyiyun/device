package com.base.repositorys;

import com.base.entitys.Device;
import com.base.entitys.DeviceType;
import com.base.entitys.DeviceTypePort;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by lx on 2016/11/25.
 */
@Repository
public interface DeviceDao extends CrudRepository<Device,Long> {
   Page<Device> findAll(Pageable page);
}
