package com.gogopet.backend.model.entity;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.google.gson.annotations.Expose;

import lombok.Data;

@Data
@Entity
@Table(name="order", schema="public")
public class Order {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Expose private long id;
	@Column(name="email", nullable = false)
	@Expose private String email;
	@Column(name="address", nullable = false)
	@Expose private String address;
	@Column(name="phone", nullable = false)
	@Expose private String phone;
	@Column(name="status", nullable = false)
	@Expose private String status;
	@Column(name="amount", nullable = false)
	@Expose private double amount;
	@Column(name="create_time", nullable = false)
	@Expose private Date create_time;
	@Column(name="update_time", nullable = false)
	@Expose private Date update_time;

	@Expose
	@OneToMany(mappedBy = "order", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<OrderDetail> orderDtailList;
}
