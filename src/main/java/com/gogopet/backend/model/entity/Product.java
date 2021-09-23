package com.gogopet.backend.model.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import com.google.gson.annotations.Expose;

import lombok.Data;
import lombok.ToString;

@ToString
@Data
@Entity
@Table(name="product", schema="public")
public class Product {

	@Id
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(name = "uuid", strategy = "uuid2")
	@Expose private String id;	
	@Column(name="color", nullable = false)
	@Expose private String color;
	@Column(name="inventory", nullable = false)
	@Expose private int inventory;
	@Column(name="amount", nullable = false)
	@Expose private int amount;
	@Column(name="create_time", nullable = false)
	@Expose private Date createTime;
	@Column(name="update_time", nullable = false)
	@Expose private Date updateTime;

	@ManyToOne
	@JoinColumn(name="product_info_id", nullable = false)
	private ProductInfo productInfo;
	
}
