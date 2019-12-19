//package com.m3bi.hotelbooking.security;
//
//import static org.springframework.security.core.userdetails.User.withUsername;
//
//import java.util.Optional;
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
//		User user = userRepository.findByUsername(username).orElseThrow(() ->
//        new UsernameNotFoundException(String.format("User with name %s does not exist", username)));
//
//		return withUsername(user.getUsername())
//		        .password(user.getPassword())
//		        .authorities("USER")
//		        .accountExpired(false)
//		        .accountLocked(false)
//		        .credentialsExpired(false)
//		        .disabled(false)
//		        .build();
//			}
//
//}