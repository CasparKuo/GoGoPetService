package com.gogopet.backend.model.dao;

import org.springframework.data.repository.CrudRepository;

import com.gogopet.backend.model.entity.Category;

public interface CategoryDao extends CrudRepository<Category, Long> {

}
