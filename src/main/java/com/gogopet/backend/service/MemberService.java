package com.gogopet.backend.service;

import java.util.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.gogopet.backend.config.JwtUser;
import com.gogopet.backend.config.JwtUserFactory;
import com.gogopet.backend.model.dao.MemberInfoDao;
import com.gogopet.backend.model.dto.MemberSignupDto;
import com.gogopet.backend.model.entity.MemberInfo;

@Service
public class MemberService {

	@Autowired
	private JwtUserFactory jwtUserFactory;
	@Autowired
	private MemberInfoDao dao;
	@Autowired
	private PasswordEncoder passwordEncoder;

	public Iterable<MemberInfo> getAll() {
		return dao.findAll();
	}
	
	public Optional<MemberInfo> getMemberByEmail(String email) {
		return dao.findById(email);
	}
	
	public MemberInfo signup(MemberSignupDto dto) {
		Optional<MemberInfo> memberOptional = dao.findById(dto.getEmail());
		
		if(memberOptional.isEmpty()) {
			MemberInfo member = new MemberInfo();
			member.setEmail(dto.getEmail());
			member.setPassword(passwordEncoder.encode(dto.getPassword()));
			member.setName(dto.getName());
			member.setGender(dto.getGender());
			member.setBirthday(dto.getBitrhday());
			member.setPhone(dto.getPhone());
			member.setAddress(dto.getAddress());
			member.setCreateTime(new Date());
			
			member = dao.save(member);
			if(member != null) {
				loginAfterSignup(member);
				return member;
			}
		}
		return null;
	}
	
	private void loginAfterSignup(MemberInfo member) {
		JwtUser user = jwtUserFactory.create(member);
		
		Authentication authentication = new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
	    SecurityContextHolder.getContext().setAuthentication(authentication);
	}
	
}
