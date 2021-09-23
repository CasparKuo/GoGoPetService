package com.gogopet.backend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gogopet.backend.model.entity.Category;
import com.gogopet.backend.model.entity.ProductInfo;
import com.gogopet.backend.service.ProductService;

@RestController
@RequestMapping("product")
public class ProductController {

	@Autowired
	private ProductService productService;
	
	@GetMapping("category")
	public ResponseEntity<List<Category>> getCategory(){
		return ResponseEntity.ok(productService.getAllCategory());
	}
	
	@GetMapping({"","{type}"})
	public ResponseEntity<List<ProductInfo>> getProductList(@PathVariable(name = "type", required = false) String type){
		System.out.println(type);
		return ResponseEntity.ok(productService.getProductInfo(type));
	}
	
	@GetMapping("detail/{id}")
	public ResponseEntity<ProductInfo> getProduct(@PathVariable(name = "id") long id) {
		return ResponseEntity.ok(productService.getProductInfoById(id));
	}
}
