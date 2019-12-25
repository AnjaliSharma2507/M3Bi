package com.m3bi.hotelbooking.application.test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertTrue;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.m3bi.hotelbooking.entity.User;
import com.m3bi.hotelbooking.repository.UserRepository;

@RunWith(SpringRunner.class)
@DataMongoTest
class HotelBookingApplicationDBTests {
	
	@Autowired
	private UserRepository userRepository;

	@Test
	public void whenFindByAdminUserName() {
		Optional<User> user = userRepository.findByUsername("admin@admin.com");
		assertTrue(user.isPresent());
		if(user.isPresent()) {
			assertThat(user.get().getName())
		      .isEqualTo("admin");
		}
	}

}
