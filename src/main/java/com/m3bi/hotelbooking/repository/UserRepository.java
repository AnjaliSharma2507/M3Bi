package com.m3bi.hotelbooking.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.m3bi.hotelbooking.entity.User;


@Repository
public interface UserRepository extends CrudRepository<User, String>{
	public Optional<User> findByName(String name);
	public Optional<User> findByUsername(String userName);
	
	@Query("{ '_id': ?0}")
	public Optional<User> findById(String id);

}
