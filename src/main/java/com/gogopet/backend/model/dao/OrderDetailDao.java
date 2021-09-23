package com.gogopet.backend.model.dao;

import org.springframework.data.repository.CrudRepository;

import com.gogopet.backend.model.entity.OrderDetail;

public interface OrderDetailDao extends CrudRepository<OrderDetail, Long> {

	public OrderDetail findByOrderId(long orderId);
}
