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
@Table(name="product_image", schema="public")
public class ProductImage {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Expose private String id;
//	@Column(name="product_info_id", nullable = false)
//	private long productInfoId;
	@Column(name="url", nullable = false)
	@Expose private String url;


	@ManyToOne
	@JoinColumn(name="product_info_id", nullable = false)
	private ProductInfo productInfo;
	
}
