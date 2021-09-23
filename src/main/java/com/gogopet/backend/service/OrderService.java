package com.gogopet.backend.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gogopet.backend.constant.OrderStatus;
import com.gogopet.backend.model.dao.OrderDao;
import com.gogopet.backend.model.dao.OrderDetailDao;
import com.gogopet.backend.model.dao.ProductDao;
import com.gogopet.backend.model.dto.OrderDto;
import com.gogopet.backend.model.dto.ProductDto;
import com.gogopet.backend.model.entity.Order;
import com.gogopet.backend.model.entity.OrderDetail;
import com.gogopet.backend.model.entity.Product;
import com.gogopet.backend.util.GsonUtil;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class OrderService {
	
	@Autowired
	private GsonUtil gsonUtil;
	
	@Autowired
	private OrderDao orderDao;
	@Autowired
	private OrderDetailDao orderDetailDao;
	
	@Autowired
	private ProductDao productDao;
	
	public List<Order> getOrderByEmail(String email) {
		return orderDao.findByEmail(email);
	}
	
	public OrderDetail getOrderDetailByOrderId(long orderId) {
		return orderDetailDao.findByOrderId(orderId);
	}
	
	private int processProductList(List<ProductDto> list, Order order, List<OrderDetail> orderDetailList) {
		int totalAmount = 0;
		
		for (ProductDto dto : list) {
			Product product = productDao.findById(dto.getProductId()).get();
			totalAmount += product.getAmount() * dto.getNumber();
			
			OrderDetail detail = new OrderDetail();
			detail.setProductId(product.getId());
			detail.setNumber(dto.getNumber());
			detail.setOrder(order);
			
			orderDetailList.add(detail);
		}
		return totalAmount;
	}
	
	public long createOrder(OrderDto dto) {
		Order order = new Order();
		order.setEmail(dto.getEmail());
		order.setAddress(dto.getAddress());
		order.setPhone(dto.getPhone());
		order.setCreate_time(new Date());
		order.setUpdate_time(new Date());
		order.setStatus(OrderStatus.INIT.name());
		
		List<OrderDetail> orderDetailList = new ArrayList<OrderDetail>();
		int totalAmount = this.processProductList(dto.getProductList(), order, orderDetailList);
		
		order.setAmount(totalAmount);
		order.setOrderDtailList(orderDetailList);
		Order orderResult = orderDao.save(order);
		
		log.info(String.format("order(%s) in db: ", order.getId()) + gsonUtil.getGsonWithExpose().toJson(order));
		
		if(orderResult != null)
			return orderResult.getId();
		return -1;
	}
	
	public long updateOrder(OrderDto dto) {	
		Optional<Order> orderOptional = orderDao.findById(dto.getId());
		if(orderOptional.isPresent()) {
			Order order = orderOptional.get();
			
			List<OrderDetail> orderDetailList = new ArrayList<OrderDetail>();
			int totalAmount = this.processProductList(dto.getProductList(), order, orderDetailList);
			
			order.setAmount(totalAmount);
			order.setOrderDtailList(orderDetailList);
			order.setUpdate_time(new Date());
			Order orderResult = orderDao.save(order);
			
			log.info("update order in db: " + gsonUtil.getGsonWithExpose().toJson(order));
			
			if(orderResult != null)
				return orderResult.getId();
		}
		return -1;
	} 
}
