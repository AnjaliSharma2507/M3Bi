package com.m3bi.hotelbooking.repository;

import java.util.Optional;
import java.util.Set;

import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.m3bi.hotelbooking.entity.BookingDetails;
import com.m3bi.hotelbooking.entity.User;

@Repository
public interface BookingDetailsRepository  extends CrudRepository<BookingDetails, String>{

	@Query("{ 'user._id': ?0}")
	public Set<BookingDetails> findByUser(String id);

	public Optional<BookingDetails> findByBookingId(long id);



}
