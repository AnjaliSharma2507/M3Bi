package com.m3bi.hotelbooking.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.m3bi.hotelbooking.exception.CustomException;
import com.m3bi.hotelbooking.model.CustomResponse;
import com.m3bi.hotelbooking.service.UserService;

@RestController
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@GetMapping("/all")
	@ResponseBody
	public CustomResponse getAllUsers() throws CustomException{
		
		return new CustomResponse(true, null,userService.getAllUsers());
	}
	
	@GetMapping
	@ResponseBody
	public CustomResponse getUser(@RequestHeader("id") Long userid) throws CustomException{
		
		return new CustomResponse(true, null,userService.getUser(userid));
	}

}
