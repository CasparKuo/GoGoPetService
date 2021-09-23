package com.gogopet.backend.model.dto;

import java.util.Date;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import lombok.Data;

@Data
public class MemberSignupDto {

	@NotNull
	@Email
	private String email;
	@NotNull
	private String password;
	@NotNull
	private String name;
	@NotNull
	@Length(min=1, max=1)
	private String gender;
	@NotNull
	private Date bitrhday;
	@NotNull
	private String phone;
	private String address;
}
