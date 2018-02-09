package com.ujigu.secure.demo.service;

import com.ujigu.secure.db.dao.IBaseDao;
import com.ujigu.secure.db.service.AbstractBaseService;
import com.ujigu.secure.demo.dao.OrderDao;
import com.ujigu.secure.demo.entity.Order;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;

@Service
public class OrderService extends AbstractBaseService<Integer, Order> {
    @Resource
    private OrderDao orderDao;

    @Override
    protected IBaseDao<Integer, Order> getBaseDao() {
        return orderDao;
    }

    public Integer insertReturnPK(Order order) {
        orderDao.insertReturnPK(order);
        return order.getPK();
    }

    public Integer doSave(Order order) {
        if (order.getPK() == null) {
            return this.insertReturnPK(order);
        }
        this.update(order);
        return order.getPK();
    }
}