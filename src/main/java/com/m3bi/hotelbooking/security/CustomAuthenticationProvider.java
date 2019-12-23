package com.m3bi.hotelbooking.security;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

import com.m3bi.hotelbooking.entity.User;
import com.m3bi.hotelbooking.repository.UserRepository;

@Component
public class CustomAuthenticationProvider implements AuthenticationProvider {
	@Autowired 
	private UserRepository userRepository;

@Override
public Authentication authenticate(Authentication authentication) throws AuthenticationException {
	String name = authentication.getName();
    String password = authentication.getCredentials().toString();
    Optional<User> user = userRepository.findByUsername(authentication.getName());
    if(user.isPresent()) {
    	if(user.get().getPassword().equals(password)) {
    		UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(name, password, new ArrayList<>());
            token.setDetails(user);
            return token;
    		
    	}
    	else {
    		return null;
    	}
    }else {
    	return null;
    }
    } 

@Override
public boolean supports(Class<?> authentication) {
	return authentication.equals(UsernamePasswordAuthenticationToken.class);
}
}