package com.m3bi.hotelbooking.service;

import java.util.List;

import com.m3bi.hotelbooking.entity.User;
import com.m3bi.hotelbooking.exception.CustomException;
import com.m3bi.hotelbooking.model.UserDetails;

public interface UserService {
	
	public List<User> getAllUsers() throws CustomException;
	public User getUser(String userId) throws CustomException;
	public User addUser(String userid, UserDetails userDetails);
}
