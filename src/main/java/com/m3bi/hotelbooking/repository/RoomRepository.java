package com.m3bi.hotelbooking.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.m3bi.hotelbooking.entity.Room;

@Repository
public interface RoomRepository extends CrudRepository<Room, Long>{

}
