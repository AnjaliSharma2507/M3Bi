package com.m3bi.hotelbooking.repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.m3bi.hotelbooking.entity.Room;

@Repository
public interface RoomRepository extends CrudRepository<Room, String>{
	@Query("{ 'roomId': ?0}")
	Optional<Room> findByRoomId(long id);
}
