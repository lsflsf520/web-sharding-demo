package com.ujigu.secure.demo.service;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ujigu.secure.common.bean.CommonStatus;
import com.ujigu.secure.db.dao.IBaseDao;
import com.ujigu.secure.db.service.AbstractBaseService;
import com.ujigu.secure.demo.dao.OrderDao;
import com.ujigu.secure.demo.entity.Order;
import com.ujigu.secure.demo.remote.IOrderService;

@Service
public class OrderService extends AbstractBaseService<Integer, Order> implements IOrderService {
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
    	order.setLastUptime(new Date());
        if (order.getPK() == null) {
        	order.setStatus(CommonStatus.NORMAL);
        	order.setCreateTime(order.getLastUptime());
            return this.insertReturnPK(order);
        }
        this.update(order);
        return order.getPK();
    }
    
    public Order loadByUidOrdId(Integer userId, int orderId){
    	Order query = new Order();
    	query.setUserId(userId);
    	query.setOrderId(orderId);
    	
    	return this.findOne(query);
    }
    
    public boolean updatePrice(Integer userId, int orderId, int price){
    	Order upData = new Order();
		upData.setOrderId(orderId);
		upData.setUserId(userId);
		upData.setPrice(price);
		upData.setLastUptime(new Date());
		
		return this.update(upData);
    }

	@Override
	public List<Order> loadMyOrders(int userId) {
		Order query = new Order();
    	query.setUserId(userId);
    	
		return this.findByEntity(query, "price.desc");
	}
}