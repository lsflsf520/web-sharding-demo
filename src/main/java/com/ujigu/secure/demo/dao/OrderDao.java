package com.ujigu.secure.demo.dao;

import org.springframework.stereotype.Repository;

import com.ujigu.secure.db.dao.IBaseDao;
import com.ujigu.secure.demo.entity.Order;

@Repository
public interface OrderDao extends IBaseDao<Integer, Order> {
    Integer insertReturnPK(Order order);
    
    void createIfNotExistsTable();
    
    void truncateTable();
    
    void dropTable();
}