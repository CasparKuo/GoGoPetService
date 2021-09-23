package com.gogopet.backend.model.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name="member_info", schema="public")
public class MemberInfo {

	@Id
	@Column(name="email", nullable = false)
	private String email;
	@Column(name="password", nullable = false)
	private String password;
	@Column(name="name", nullable = false)
	private String name;
	@Column(name="gender", nullable = false, length=1)
	private String gender;
	@Column(name="birthday", nullable = false)
	private Date birthday;
	@Column(name="address")
	private String address;
	@Column(name="phone", nullable = false)
	private String phone;
	@Column(name="create_time", nullable = false)
	private Date createTime;
}
