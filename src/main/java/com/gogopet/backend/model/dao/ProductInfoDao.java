package com.gogopet.backend.model.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.gogopet.backend.model.entity.ProductInfo;

public interface ProductInfoDao extends CrudRepository<ProductInfo, Long> {

	public List<ProductInfo> findByCategoryId(String categoryId);
}
