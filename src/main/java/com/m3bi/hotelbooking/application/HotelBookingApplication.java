package com.m3bi.hotelbooking.application;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.m3bi.hotelbooking.repository.RoomRepository;
import com.m3bi.hotelbooking.repository.UserRepository;
import com.m3bi.hotelbooking.security.WebSecurityConfiguration;
import com.m3bi.hotelbooking.entity.Room;
import com.m3bi.hotelbooking.entity.User;
import com.m3bi.hotelbooking.entity.UserBonus;

@ComponentScan("com.m3bi.hotelbooking.controller")
@ComponentScan("com.m3bi.hotelbooking.service")
@ComponentScan("com.m3bi.hotelbooking.security")
@EntityScan("com.m3bi.hotelbooking.entity")
@EnableMongoRepositories("com.m3bi.hotelbooking.repository")
@SpringBootApplication(exclude = {SecurityAutoConfiguration.class})
public class HotelBookingApplication {
	


	public static void main(String[] args) {
		SpringApplication.run(HotelBookingApplication.class, args);
	}
	
	@Bean
    CommandLineRunner init(UserRepository userRepository, RoomRepository roomRepository) {
		return args -> {
	          userRepository.findAll().forEach(System.out::println);
	          roomRepository.findAll().forEach(System.out::println);
			
		};	       
	}

}
