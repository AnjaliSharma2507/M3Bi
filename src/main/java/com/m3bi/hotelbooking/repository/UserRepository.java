package com.m3bi.hotelbooking.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.m3bi.hotelbooking.entity.User;


@Repository
public interface UserRepository extends CrudRepository<User, Long>{
	public User findByName(String name);

}
