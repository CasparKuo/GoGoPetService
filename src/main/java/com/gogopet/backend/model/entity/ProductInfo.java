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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import com.google.gson.annotations.Expose;

import lombok.Data;

@Data
@Entity
@Table(name="product_info", schema="public")
public class ProductInfo {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Expose private long id;
	@Column(name="name", nullable = false)
	@Expose private String name;
	@Column(name="description", nullable = true)
	@Expose private String description;
	@Column(name="create_time", nullable = false)
	@Expose private Date createTime;

	@Expose
	@ManyToOne
	@JoinColumn(name="category_id", nullable = false)
	private Category category;
	@Expose
	@OneToMany(mappedBy = "productInfo", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<Product> productList;
	@Expose
	@NotFound(action = NotFoundAction.IGNORE)
	@OneToMany(mappedBy = "productInfo", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<ProductImage> productImageList;
	
}
