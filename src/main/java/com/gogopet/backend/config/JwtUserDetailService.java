package com.gogopet.backend.config;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.gogopet.backend.model.entity.MemberInfo;
import com.gogopet.backend.service.MemberService;

@Service
public class JwtUserDetailService implements UserDetailsService {

	@Autowired
	private JwtUserFactory jwtFactory;
	@Autowired
	private MemberService memberService;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		Optional<MemberInfo> member = memberService.getMemberByEmail(username);
		
		if(member.isEmpty()) {
            throw new UsernameNotFoundException(String.format("No user found with username '%s'.", username));
		}
		return getUserDetails(member.get());
	}
	
	private UserDetails getUserDetails(MemberInfo member) {
		return jwtFactory.create(member);
	}

}
