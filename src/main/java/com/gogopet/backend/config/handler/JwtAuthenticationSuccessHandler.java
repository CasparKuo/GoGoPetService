package com.gogopet.backend.config.handler;

import java.io.IOException;
import java.io.PrintWriter;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import com.gogopet.backend.util.JWTUtil;

@Component
public class JwtAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

	@Autowired
	private JWTUtil jwtUtil;
	
	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws IOException, ServletException {
		System.out.println("account(" + authentication.getName() + ") Login success");
        PrintWriter out = response.getWriter();
        response.setStatus(200);
        try {
			out.write(jwtUtil.createToken(authentication.getName()));
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvalidKeySpecException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        out.flush();
        out.close();
		
	}
 

 }