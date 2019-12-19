package com.m3bi.hotelbooking.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.m3bi.hotelbooking.entity.BookingDetails;
import com.m3bi.hotelbooking.model.RequestRoomDetails;
import com.m3bi.hotelbooking.model.RoomDetails;

public interface RoomService {
	
	public List<RoomDetails> getAllRooms(Long userId) throws Exception;
	

	BookingDetails bookARoom(RequestRoomDetails roomDetails, Long userId) throws Exception;

}
