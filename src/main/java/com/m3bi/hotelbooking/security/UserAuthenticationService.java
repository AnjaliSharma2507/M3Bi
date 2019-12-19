//package com.m3bi.hotelbooking.security;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.stereotype.Service;
//
//import com.m3bi.hotelbooking.entity.User;
//import com.m3bi.hotelbooking.repository.UserRepository;
//
//@Service
//public class UserAuthenticationService implements UserDetailsService{
//	
//	@Autowired
//	private UserRepository userRepository;
//
//	@Override
//	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//		System.out.println("User::::"+username);
//		User user =  userRepository.findByName(username);
//		if(user == null) {
//			throw new UsernameNotFoundException("No user present with username: "+username);
//		}
//		return new AuthenticatedUser(user);
//	}
//
//}
