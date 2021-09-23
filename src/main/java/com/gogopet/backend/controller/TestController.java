package com.gogopet.backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gogopet.backend.service.MemberService;
import com.google.gson.Gson;

@RestController
public class TestController {

	@Autowired
	private Gson gson;
	@Autowired
	private MemberService memberService;
	
	@GetMapping("test")
	public String test() {
		
		return "test";
	}
	
	@GetMapping("get")
	public String get() {
		return gson.toJson(memberService.getAll());
	}
	
}
