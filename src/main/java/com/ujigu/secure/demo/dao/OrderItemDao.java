package com.ujigu.secure.demo.dao;

import org.springframework.stereotype.Repository;

import com.ujigu.secure.db.dao.IBaseDao;
import com.ujigu.secure.demo.entity.OrderItem;

@Repository
public interface OrderItemDao extends IBaseDao<Integer, OrderItem> {
    Integer insertReturnPK(OrderItem orderItem);
    
    void createIfNotExistsTable();
    
    void truncateTable();
    
    void dropTable();
}