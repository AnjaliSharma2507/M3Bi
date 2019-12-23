package com.m3bi.hotelbooking.controller;


import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.m3bi.hotelbooking.model.CustomResponse;


@RestController
@RequestMapping("/login")
public class LoginController {
	
	@GetMapping
	@ResponseBody
	public CustomResponse login() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String currentPrincipalName = authentication.getName();
		return new CustomResponse(true, null, currentPrincipalName+" logged in successfully.");
		
	}

}
