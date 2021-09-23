package com.gogopet.backend.model.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.gogopet.backend.model.entity.Product;

public interface ProductDao extends CrudRepository<Product, String> {

	public List<Product> findByProductInfoId(long id);
	
	
	@Query(value = "select amount from product where id = ?1", nativeQuery = true)
	public int findamountById(long id);
}
