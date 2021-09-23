package com.gogopet.backend.config.handler;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import com.google.gson.Gson;

@Component
public class JwtAuthenticationFailureHandler implements AuthenticationFailureHandler {

	@Autowired
	private Gson gson;
	
	@Override
	public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException exception) throws IOException, ServletException {
		response.setContentType("application/json;charset=UTF-8");
        PrintWriter out = response.getWriter();
        response.setStatus(HttpServletResponse.SC_NOT_FOUND);
        Map<String, String> result = Map.of("message", "登入失敗");
        out.write(gson.toJson(result));
        out.flush();
        out.close();
    
		
	}

}
