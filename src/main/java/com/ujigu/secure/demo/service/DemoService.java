package com.ujigu.secure.demo.service;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ujigu.secure.demo.dao.OrderDao;
import com.ujigu.secure.demo.dao.OrderItemDao;
import com.ujigu.secure.demo.entity.Order;
import com.ujigu.secure.demo.entity.OrderItem;


@Service
public class DemoService {
    
    @Resource
    private OrderDao orderRepository;
    
    @Resource
    private OrderItemDao orderItemRepository;
    
    public void demo() {
        orderRepository.createIfNotExistsTable();
        orderItemRepository.createIfNotExistsTable();
        orderRepository.truncateTable();
        orderItemRepository.truncateTable();
        List<Long> orderIds = new ArrayList<>(10);
        System.out.println("1.Insert--------------");
        for (int i = 0; i < 10; i++) {
            Order order = new Order();
//            order.setOrderId(100+ i);
            order.setUserId(100+i);
            order.setStatus("NORMAL");
            orderRepository.insertPK(order);
            long orderId = order.getOrderId();
            orderIds.add(orderId);
            
            OrderItem item = new OrderItem();
            item.setOrderId(orderId);
            item.setUserId(100+i);
            item.setStatus("NORMAL");
            orderItemRepository.insertPK(item);
        }
        System.out.println(orderItemRepository.selectAll());
        System.out.println("2.Delete--------------");
//        for (Long each : orderIds) {
//            orderRepository.delete(each);
//            orderItemRepository.delete(each);
//        }
//        System.out.println(orderItemRepository.selectAll());
//        orderItemRepository.dropTable();
//        orderRepository.dropTable();
    }
}
