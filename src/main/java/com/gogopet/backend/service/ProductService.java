package com.gogopet.backend.service;

import java.util.List;
import java.util.stream.StreamSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.gogopet.backend.model.dao.CategoryDao;
import com.gogopet.backend.model.dao.ProductDao;
import com.gogopet.backend.model.dao.ProductImageDao;
import com.gogopet.backend.model.dao.ProductInfoDao;
import com.gogopet.backend.model.entity.Category;
import com.gogopet.backend.model.entity.ProductInfo;

@Service
public class ProductService {

	@Autowired
	private CategoryDao categoryDao;
	@Autowired
	private ProductInfoDao productInfoDao;
	@Autowired
	private ProductDao productDao;
	@Autowired
	private ProductImageDao productImageDao;
	
	private <T> List<T> IterableToList(Iterable<T> source) {
		return StreamSupport.stream(source.spliterator(), false).toList();
	}
	
	public List<Category> getAllCategory() {
		return IterableToList(categoryDao.findAll());
	}
	
	private List<ProductInfo> getAllProductInfo() {
		return IterableToList(productInfoDao.findAll());
	}
	
	private List<ProductInfo> getProductInfoByCategoryId(String id) {
		return productInfoDao.findByCategoryId(id);
	}
	
	public List<ProductInfo> getProductInfo(String type) {
		if(StringUtils.hasLength(type)) 
			return this.getAllProductInfo();
		else
			return this.getProductInfoByCategoryId(type);
	}
	
	public ProductInfo getProductInfoById(long id){
		return productInfoDao.findById(id).get();
	}
}
