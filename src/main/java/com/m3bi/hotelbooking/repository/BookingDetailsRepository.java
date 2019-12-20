package com.m3bi.hotelbooking.repository;

import java.util.Set;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.m3bi.hotelbooking.entity.BookingDetails;
import com.m3bi.hotelbooking.entity.User;

@Repository
public interface BookingDetailsRepository  extends CrudRepository<BookingDetails, Long>{

	public Set<BookingDetails> findByUser(User user);


}
