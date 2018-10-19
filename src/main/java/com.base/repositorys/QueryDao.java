package com.base.repositorys;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Map;

/**
 * Created by liutim on 2016/10/9.
 */
public interface QueryDao {

    /**
     * 复杂分页查询
     * @param sql -不可待order子句，order子句应该放到pageable中
     * @param pageable
     * @return
     */

    public Page<Map<String,Object>> query(String sql, Pageable pageable);

    /**
     * sql语句返回数据集的数量
     * @param sql 不可带order子句
     * @return
     */
    public long count(String sql);

    /**
     * 复杂的sql语句
     * @param sql
     * @return
     */

    public List queryfind(String sql);
}
