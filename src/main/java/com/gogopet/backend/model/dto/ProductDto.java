package com.gogopet.backend.model.dto;

import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
public class ProductDto {

	@NotNull
	private String productId;
	@NotNull
	private int number; 
}
