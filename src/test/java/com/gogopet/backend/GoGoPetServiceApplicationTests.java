package com.gogopet.backend;

import java.util.Date;

import org.assertj.core.util.Lists;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import com.gogopet.backend.model.dao.CategoryDao;
import com.gogopet.backend.model.dao.ProductInfoDao;
import com.gogopet.backend.model.entity.Category;
import com.gogopet.backend.model.entity.Product;
import com.gogopet.backend.model.entity.ProductImage;
import com.gogopet.backend.model.entity.ProductInfo;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

@SpringBootTest
class GoGoPetServiceApplicationTests {

//	private Gson gson = new GsonBuilder()
//			   .setDateFormat("yyyy-MM-dd HH:mm:ss").create();

	@Autowired
	private Gson gson;
	
	@Autowired
	private ProductInfoDao pidao;
	@Autowired
	private CategoryDao cateDao;

	@Test
	void contextLoads() {
		insertProduct();

		Assertions.assertEquals(true, true);
	}

	@Transactional
	private void insertProduct() {
		Gson gson = new GsonBuilder()
				.excludeFieldsWithoutExposeAnnotation()
				.setDateFormat("yyyy-MM-dd HH:mm:ss")
				.create();
		
		Category cate = cateDao.findById(38L).get();

		System.out.println("category: " + gson.toJson(cate));

		Product product = new Product();
		product.setInventory(10);
		product.setAmount(500);
		product.setColor("white");
		product.setCreateTime(new Date());
		product.setUpdateTime(new Date());
		
		Product product2 = new Product();
		product2.setInventory(20);
		product2.setAmount(1000);
		product2.setColor("black");
		product2.setCreateTime(new Date());
		product2.setUpdateTime(new Date());

		System.out.println("product: " + gson.toJson(product));

		ProductInfo info = new ProductInfo();
		info.setName("測試產品2");
		info.setDescription("test \ntest line2.");
		info.setCategory(cate);
		info.setCreateTime(new Date());

		product.setProductInfo(info);
		product2.setProductInfo(info);
		
		info.setProductList(Lists.list(product, product2));
		info.setProductImageList(null);

		System.out.println("info: " + gson.toJson(info));

		pidao.save(info);

		Iterable<ProductInfo> infoList = pidao.findAll();
		
		System.out.println(gson.toJson(infoList));

	}
}
