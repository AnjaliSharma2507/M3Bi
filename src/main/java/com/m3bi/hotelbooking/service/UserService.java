package com.m3bi.hotelbooking.service;

import java.util.List;

import com.m3bi.hotelbooking.entity.User;
import com.m3bi.hotelbooking.exception.CustomException;

public interface UserService {
	
	public List<User> getAllUsers() throws CustomException;
	public User getUser(Long userId) throws CustomException;
}
