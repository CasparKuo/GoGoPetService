package com.gogopet.backend.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.google.gson.annotations.Expose;

import lombok.Data;

@Data
@Entity
@Table(name="order_detail", schema="public")
public class OrderDetail {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Expose private long id;
//	@Column(name="order_id", nullable = false)
//	private long orderId;
	@Column(name="product_id", nullable = false)
	@Expose private String productId;
	@Column(name="number", nullable = false)
	@Expose private int number;
	
	@ManyToOne
	@JoinColumn(name="order_id", nullable = false)
	private Order order;
}
