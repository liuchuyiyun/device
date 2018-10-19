package com.base.repositorys;

import com.base.entitys.Gate;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by lx on 2016/12/1.
 */
@Repository
public interface GateDao extends PagingAndSortingRepository<Gate,Long> {

    List<Gate> findByGateIDAndConnectnameAndConnnectpass(String gateID,String name,String pass);
    List<Gate> findByGateID(String gateID);
    List<Gate> findByConnectnameAndConnnectpass(String name,String pass);
    List<Gate> findByConnectname(String name);

    Page<Gate> findAll(Pageable pageable);

}
