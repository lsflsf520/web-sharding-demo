package com.ujigu.secure.demo.service;

import com.ujigu.secure.db.dao.IBaseDao;
import com.ujigu.secure.db.service.AbstractBaseService;
import com.ujigu.secure.demo.dao.OrderItemDao;
import com.ujigu.secure.demo.entity.OrderItem;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;

@Service
public class OrderItemService extends AbstractBaseService<Integer, OrderItem> {
    @Resource
    private OrderItemDao orderItemDao;

    @Override
    protected IBaseDao<Integer, OrderItem> getBaseDao() {
        return orderItemDao;
    }

    public Integer insertReturnPK(OrderItem orderItem) {
        orderItemDao.insertReturnPK(orderItem);
        return orderItem.getPK();
    }

    public Integer doSave(OrderItem orderItem) {
        if (orderItem.getPK() == null) {
            return this.insertReturnPK(orderItem);
        }
        this.update(orderItem);
        return orderItem.getPK();
    }
}