package com.m3bi.hotelbooking.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.m3bi.hotelbooking.entity.User;
import com.m3bi.hotelbooking.entity.UserBonus;

@Repository
public interface UserBonusRepository extends CrudRepository<UserBonus, Long>{
	public UserBonus findByUser(User user);

}
