package com.m3bi.hotelbooking.service.Implementations;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.m3bi.hotelbooking.entity.BookingDetails;
import com.m3bi.hotelbooking.entity.Room;
import com.m3bi.hotelbooking.entity.User;
import com.m3bi.hotelbooking.exception.CustomException;
import com.m3bi.hotelbooking.model.BookingStatus;
import com.m3bi.hotelbooking.model.RequestRoomDetails;
import com.m3bi.hotelbooking.model.RoomDetails;
import com.m3bi.hotelbooking.repository.BookingDetailsRepository;
import com.m3bi.hotelbooking.repository.RoomRepository;
import com.m3bi.hotelbooking.repository.UserBonusRepository;
import com.m3bi.hotelbooking.repository.UserRepository;
import com.m3bi.hotelbooking.service.RoomService;
import com.m3bi.hotelbooking.service.UserService;
import com.sun.jmx.snmp.Timestamp;

@Service
public class RoomServiceImpl implements RoomService{
	
	@Autowired
	private RoomRepository roomRepository;
	
	@Autowired
	private UserService userService;

	@Override
	public List<RoomDetails> getAllRooms(Long userId) throws CustomException {
		List<Room> rooms = (List<Room>)roomRepository.findAll();
		List<RoomDetails> roomDetails;
		if(userId != null) {
			User userDetails = userService.getUser(userId);
			long bonus = userDetails.getBonuspoints().getBonusPoint();
			roomDetails = rooms.stream().map(room -> new RoomDetails(room,bonus)).collect(Collectors.toList());
			}
			else {
			roomDetails = rooms.stream().map(room -> new RoomDetails(room, 0)).collect(Collectors.toList());
		}
		return roomDetails;
	}

	

}
