package com.ujigu.secure.demo.controller;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ujigu.secure.common.bean.CommonStatus;
import com.ujigu.secure.common.bean.ResultModel;
import com.ujigu.secure.common.utils.RandomUtil;
import com.ujigu.secure.demo.entity.Order;
import com.ujigu.secure.demo.entity.OrderItem;
import com.ujigu.secure.demo.service.OrderItemService;
import com.ujigu.secure.demo.service.OrderService;

@Controller
@RequestMapping("demo/order")
public class OrderController {

	@Resource
	private OrderService orderService;
	
	@Resource
	private OrderItemService orderItemService;
	
	@RequestMapping("create")
	@ResponseBody
	public ResultModel createOrder(int userId){
		Order upData = new Order();
		upData.setUserId(userId);
		upData.setPrice(RandomUtil.rand(10000));
		upData.setStatus(CommonStatus.NORMAL);
		upData.setLastUptime(new Date());
		upData.setCreateTime(new Date());
		
		int orderId = orderService.insertReturnPK(upData);
		
		OrderItem upItem1 = new OrderItem();
		upItem1.setOrderId(orderId);
		upItem1.setUserId(userId);
		upItem1.setGoods(RandomUtil.randomAlphaNumCode(20));
		upItem1.setStatus(CommonStatus.NORMAL);
		upItem1.setLastUptime(new Date());
		upItem1.setCreateTime(new Date());
		
		OrderItem upItem2 = new OrderItem();
		upItem2.setOrderId(orderId);
		upItem2.setUserId(userId);
		upItem2.setGoods(RandomUtil.randomAlphaNumCode(20));
		upItem2.setStatus(CommonStatus.NORMAL);
		upItem2.setLastUptime(new Date());
		upItem2.setCreateTime(new Date());
		
		orderItemService.insert(upItem1);
		orderItemService.insert(upItem2);
		
//		orderItemService.insertBatch(Arrays.asList(item1, item2));
		
		return new ResultModel(orderId);
	}
	
	@RequestMapping("query")
	@ResponseBody
	public ResultModel queryOrder(Integer userId, int orderId){
		Order dbData = orderService.loadByUidOrdId(userId, orderId);
		
		return new ResultModel(dbData);
	}
	
	@RequestMapping("queryItem")
	@ResponseBody
	public ResultModel queryOrderItem(Integer userId, int orderId){
		List<OrderItem> items = orderItemService.loadByOrderId(userId, orderId);
		
		return new ResultModel(items);
	}
	
	@RequestMapping("updatePrice")
	@ResponseBody
	public ResultModel updatePrice(Integer userId, int orderId, Integer price){
		
		orderService.updatePrice(userId, orderId, price == null ? RandomUtil.rand(10000) : price);
		
		Order dbData = orderService.findById(orderId);
		
		return new ResultModel(dbData);
	}
	
}
