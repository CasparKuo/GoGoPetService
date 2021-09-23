package com.gogopet.backend.model.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.gogopet.backend.model.entity.Order;

public interface OrderDao extends CrudRepository<Order, Long> {

	public List<Order> findByEmail(String email);
}
