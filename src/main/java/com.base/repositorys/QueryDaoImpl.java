package com.base.repositorys;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

/**
 * Created by liutim on 2016/10/9.
 */
@Component("queryDao")
public class QueryDaoImpl implements QueryDao {


    @Autowired
    private JdbcTemplate jdbcTemplate;

    public Page<Map<String,Object>> query(String sql, Pageable pageable) {

        long count = count(sql);


        if(pageable.getSort()!=null){
            for(Sort.Order o : pageable.getSort())
            {
                sql += " ORDER BY " + o.getProperty() + " " + o.getDirection().toString() + " ";
            }
        }

        int pageSize = pageable.getPageSize();

        sql += " LIMIT " + pageable.getPageNumber()*pageSize + "," + pageSize + " ";
        return new PageImpl<Map<String, Object>>(jdbcTemplate.queryForList(sql),pageable,count);
    }

    public List queryfind(String sql){
        return jdbcTemplate.queryForList(sql);
    }

    public long count(String sql){
        String countsql="select count(1) from ("+sql+") as _count";
        final long[] temp=new long[]{0};
        jdbcTemplate.query(countsql, new RowCallbackHandler() {
            @Override
            public void processRow(ResultSet rs) throws SQLException {
                temp[0]=rs.getLong(1);
            }
        });
        return temp[0];
    }
}
