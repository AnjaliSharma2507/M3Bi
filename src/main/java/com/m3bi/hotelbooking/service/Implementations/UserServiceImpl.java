package com.m3bi.hotelbooking.service.Implementations;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.m3bi.hotelbooking.entity.User;
import com.m3bi.hotelbooking.entity.UserBonus;
import com.m3bi.hotelbooking.exception.CustomException;
import com.m3bi.hotelbooking.model.UserDetails;
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
	public User getUser(String userId) throws CustomException {
		Optional<User> user = userRepository.findById(userId);
		if(user.isPresent()) {
			return user.get();
		}else {
			throw new CustomException("No User Found");
		}
	}

	@Override
	public User addUser(String userid, UserDetails userDetails) {
		User user = new User(userDetails.name, userDetails.email, userDetails.password);
		UserBonus bonus = new UserBonus(userDetails.getBonus());
		return userRepository.save(user);
	}

}
