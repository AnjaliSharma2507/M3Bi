package com.m3bi.hotelbooking.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.m3bi.hotelbooking.entity.User;


@Repository
public interface UserRepository extends CrudRepository<User, Long>{
	public Optional<User> findByName(String name);
	public Optional<User> findByUsername(String userName);

}
