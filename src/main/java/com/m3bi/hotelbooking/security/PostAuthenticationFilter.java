package com.m3bi.hotelbooking.security;

import java.io.IOException;
import java.util.Optional;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.GenericFilterBean;

import com.m3bi.hotelbooking.entity.User;

@Component
public class PostAuthenticationFilter extends GenericFilterBean {
	


	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException, AuthenticationException {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		response.setContentType("application/json");
		if(authentication != null) {
			Optional<User> user = (Optional<User>) authentication.getDetails();
			if(user.isPresent()) {
	            request.setAttribute("id", user.get().getId());
			}
		}
		chain.doFilter(request, response);
	}

}
