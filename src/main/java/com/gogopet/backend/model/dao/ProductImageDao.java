package com.gogopet.backend.model.dao;

import org.springframework.data.repository.CrudRepository;

import com.gogopet.backend.model.entity.ProductImage;

public interface ProductImageDao extends CrudRepository<ProductImage, Long> {

}
