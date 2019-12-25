package com.m3bi.hotelbooking.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.m3bi.hotelbooking.entity.User;
import com.m3bi.hotelbooking.exception.CustomException;
import com.m3bi.hotelbooking.model.CustomResponse;
import com.m3bi.hotelbooking.model.RequestRoomDetails;
import com.m3bi.hotelbooking.model.UserDetails;
import com.m3bi.hotelbooking.service.UserService;

@RestController
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@GetMapping("/all")
	@ResponseBody
	public CustomResponse<List<User>> getAllUsers() throws CustomException{
		
		return new CustomResponse<List<User>>(true, null,userService.getAllUsers());
	}
	
	@GetMapping
	@ResponseBody
	public CustomResponse<User> getUser(@RequestAttribute("id") String userid) throws CustomException{
		return new CustomResponse<User>(true, null,userService.getUser(userid));
	}
	
	@PostMapping
	@ResponseBody
	public CustomResponse<User> addUser(@RequestAttribute("id") String userid, @RequestBody UserDetails userDetails) throws CustomException{
		return new CustomResponse<User>(true, null,userService.addUser(userid, userDetails));
	}

}
