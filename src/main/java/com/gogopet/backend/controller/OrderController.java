package com.gogopet.backend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gogopet.backend.model.dto.OrderDto;
import com.gogopet.backend.model.entity.Order;
import com.gogopet.backend.model.entity.OrderDetail;
import com.gogopet.backend.service.OrderService;

@RestController
@RequestMapping("order")
public class OrderController {

	@Autowired
	private OrderService orderService;
	
	@GetMapping("get/{email}")
	public ResponseEntity<List<Order>> getOrderByEmail(@PathVariable(name = "email", required = true) String email) {
		return ResponseEntity.ok(orderService.getOrderByEmail(email));
	}
	
	@GetMapping("detail/{id}")
	public ResponseEntity<OrderDetail> getOrderDetailById(@PathVariable(name = "id", required = true) long id) {
		return ResponseEntity.ok(orderService.getOrderDetailByOrderId(id));
	}
	
	@PostMapping("create")
	public ResponseEntity<Long> createOrder(@RequestBody @Validated OrderDto orderDto) {
		return ResponseEntity.ok(orderService.createOrder(orderDto));
	}

	@PutMapping("update")
	public ResponseEntity<Long> updateOrder(@RequestBody @Validated({OrderDto.update.class}) OrderDto orderDto) {
		return ResponseEntity.ok(orderService.updateOrder(orderDto));
	}
}
