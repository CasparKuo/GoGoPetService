package com.gogopet.backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.gogopet.backend.model.dto.MemberSignupDto;
import com.gogopet.backend.model.entity.MemberInfo;
import com.gogopet.backend.service.MemberService;

@RestController
public class SignupController {

	@Autowired
	private MemberService memberService;
	
	@PostMapping("signup")
	public ResponseEntity<Object> signupMember(@RequestBody @Validated MemberSignupDto dto) {
		MemberInfo member = memberService.signup(dto);
		if(member != null)
			return ResponseEntity.ok("signup success!");
		return ResponseEntity.ok("signup fail!");
	}
}
