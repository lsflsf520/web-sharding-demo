package com.ujigu.secure.demo.service;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.github.miemiedev.mybatis.paginator.domain.Order.Direction;
import com.ujigu.secure.common.bean.CommonStatus;
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
    
    @Resource
    private OrderService orderService;
    
    @Resource
    private OrderItemService orderItemService;
    
    public void demo() {
        orderRepository.createIfNotExistsTable();
        orderItemRepository.createIfNotExistsTable();
        orderRepository.truncateTable();
        orderItemRepository.truncateTable();
        List<Integer> orderIds = new ArrayList<>(10);
        System.out.println("1.Insert--------------");
        for (int i = 0; i < 10; i++) {
            Order order = new Order();
//            order.setOrderId(100+ i);
            order.setUserId(100+i);
            order.setStatus(CommonStatus.NORMAL);
            orderRepository.insertReturnPK(order);
            int orderId = order.getOrderId();
            orderIds.add(orderId);
            
            OrderItem item = new OrderItem();
            item.setOrderId(orderId);
            item.setUserId(100+i);
            item.setStatus(CommonStatus.NORMAL);
            orderItemRepository.insert(item);
        }
        System.out.println(orderItemService.findAll("order_id", Direction.DESC));
        System.out.println(orderService.findAll("user_id", Direction.ASC));
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
