package com.m3bi.hotelbooking.application;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
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
@EnableJpaRepositories("com.m3bi.hotelbooking.repository")
@SpringBootApplication(exclude = {SecurityAutoConfiguration.class})
public class HotelBookingApplication {
	


	public static void main(String[] args) {
		SpringApplication.run(HotelBookingApplication.class, args);
	}
	
	@Bean
    CommandLineRunner init(UserRepository userRepository, RoomRepository roomRepository) {
		return args -> {
			  User user1 = new User("Darth Vadar", "darthvadar@gmail.com", "Darth123#");
	          user1.setBonuspoints(new UserBonus((long) 1000));
	          userRepository.save(user1);
	          
	          User user2 = new User("Obi Wan Kenobi", "obiwankenobi@gmail.com", "Obi123#");
	          user2.setBonuspoints(new UserBonus((long) 2000));
	          userRepository.save(user2);
	          
	          User user3 = new User("admin", "admin@admin.com", "admin");
	          user3.setBonuspoints(new UserBonus((long) 10000));
	          userRepository.save(user3);
	          
	          userRepository.findAll().forEach(System.out::println);
	          
	          roomRepository.save(new Room("Super Delux","1 extra-large double bed, free wifi, AC", (long)1500));
	          roomRepository.save(new Room("Fairfield King Room","1 large double bed \n This twin/double room has a flat-screen TV, electric kettle and minibar.", (long)3000));
	          roomRepository.save(new Room("Standard Double Room","1 large double bed, free wifi, AC", (long)1000));
	          roomRepository.save(new Room("Business Queen Room","1 double bed 28 m²  View  Air conditioning  Ensuite bathroom  Flat-screen TV  Minibar  Free WiFi ", (long)2000));
	          
	          roomRepository.findAll().forEach(System.out::println);
			
		};	       
	}

}
