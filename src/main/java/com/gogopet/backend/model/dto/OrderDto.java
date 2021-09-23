package com.gogopet.backend.model.dto;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
public class OrderDto {
	
	@NotNull(groups = {update.class})
	private long id;
	@NotNull
	@Email
	private String email;
	@NotNull
	private String address;
	@NotNull
	private String phone;
	@NotNull
	@Valid
	List<ProductDto> productList;
	

	public interface create{}
	public interface update{}
}
