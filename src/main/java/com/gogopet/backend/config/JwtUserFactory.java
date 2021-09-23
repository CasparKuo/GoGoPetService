package com.gogopet.backend.config;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import com.gogopet.backend.model.entity.MemberInfo;

@Component
public class JwtUserFactory {

	private String[] authorities = {"USER"}; 
	
	public JwtUser create(MemberInfo member) {
        return new JwtUser(
        		member.getEmail(),
        		member.getPassword(),
        		mapToGrantedAuthorities(authorities)
        );
    }

    private List<GrantedAuthority> mapToGrantedAuthorities(String[] authorities) {
        return Arrays.asList(authorities).stream()
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());
    }
}
