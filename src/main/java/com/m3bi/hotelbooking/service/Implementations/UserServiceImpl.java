package com.m3bi.hotelbooking.service.Implementations;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.m3bi.hotelbooking.entity.User;
import com.m3bi.hotelbooking.exception.CustomException;
import com.m3bi.hotelbooking.repository.UserRepository;

@Service
public class UserServiceImpl implements com.m3bi.hotelbooking.service.UserService {
	
	@Autowired
	private UserRepository userRepository;

	@Override
	public List<User> getAllUsers() throws CustomException {
		return (List<User>) userRepository.findAll();
	}

	@Override
	public User getUser(Long userId) throws CustomException {
		Optional<User> user = userRepository.findById(userId);
		if(user.isPresent()) {
			return user.get();
		}else {
			throw new CustomException("No User Found");
		}
	}

}
