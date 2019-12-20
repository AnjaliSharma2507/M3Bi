package com.m3bi.hotelbooking.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.m3bi.hotelbooking.exception.CustomException;
import com.m3bi.hotelbooking.model.RoomDetails;

public interface RoomService {
	
	public List<RoomDetails> getAllRooms(Long userId) throws CustomException;

}
