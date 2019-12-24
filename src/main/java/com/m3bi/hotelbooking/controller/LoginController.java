package com.m3bi.hotelbooking.controller;


import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.m3bi.hotelbooking.model.CustomResponse;


@RestController
public class LoginController {
	
	@GetMapping(value="/login", produces = "application/json")
	@ResponseBody
	public CustomResponse<String> login() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String currentPrincipalName = authentication.getName();
		return new CustomResponse<String>(true, null, currentPrincipalName+" logged in successfully.");
		
	}
	
	@GetMapping(value="/logout", produces = "application/json")
	@ResponseBody
	public CustomResponse<String> logout() {
		return new CustomResponse<String>(true, null, "User logged out successfully.");
		
	}

}
