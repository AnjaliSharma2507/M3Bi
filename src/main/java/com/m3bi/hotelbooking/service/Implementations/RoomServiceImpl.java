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
import com.m3bi.hotelbooking.model.BookingStatus;
import com.m3bi.hotelbooking.model.RequestRoomDetails;
import com.m3bi.hotelbooking.model.RoomDetails;
import com.m3bi.hotelbooking.repository.BookingDetailsRepository;
import com.m3bi.hotelbooking.repository.RoomRepository;
import com.m3bi.hotelbooking.repository.UserBonusRepository;
import com.m3bi.hotelbooking.repository.UserRepository;
import com.m3bi.hotelbooking.service.RoomService;
import com.sun.jmx.snmp.Timestamp;

@Service
public class RoomServiceImpl implements RoomService{
	
	@Autowired
	private RoomRepository roomRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private BookingDetailsRepository bookingDetailsRepository;
	
	@Autowired
	private UserBonusRepository userBonusRepository;

	@Override
	public List<RoomDetails> getAllRooms(Long userId) throws Exception {
		List<Room> rooms = (List<Room>)roomRepository.findAll();
		List<RoomDetails> roomDetails;
		User user = getUser(userId);
		if(userId != null) {
			User userDetails = getUser(userId);
			long bonus = userDetails.getBonuspoints().getBonusPoint();
			roomDetails = rooms.stream().map(room -> new RoomDetails(room,bonus)).collect(Collectors.toList());
			}
			else {
			roomDetails = rooms.stream().map(room -> new RoomDetails(room, 0)).collect(Collectors.toList());
		}
		return roomDetails;
	}

	@Override
	public BookingDetails bookARoom(RequestRoomDetails roomDetails, Long userId) throws Exception {
		BookingDetails bookingDetails = new BookingDetails();
		Optional<Room> roomOptional = null;
		Room room = null;
		User user = null;
		BookingStatus status = BookingStatus.PENDING_APPROVAL;
		/*Validations*/
		if(userId == null) {
			throw  new Exception("Please Login to book a room");
		}
	    if(roomDetails.getCheckInDate() == null || roomDetails.getCheckInDate().isEmpty()
	    || roomDetails.getCheckOutDate()== null || roomDetails.getCheckOutDate().isEmpty()||roomDetails.getRoom() == null){
	    	throw new Exception("Please provide checkin, checkout and room details.");
	    }
		LocalDate checkInDate = LocalDate.parse(roomDetails.getCheckInDate());
		LocalDate checkOutDate = LocalDate.parse(roomDetails.getCheckOutDate());
		long noOfDaysBetween = ChronoUnit.DAYS.between(checkInDate, checkOutDate);
		if(noOfDaysBetween < 1) {
			throw new Exception("Invalid Checkin And Checkout dates");
		}
		roomOptional = roomRepository.findById(roomDetails.getRoom());
		if(!roomOptional.isPresent()) {
			throw new Exception("Invalid Room Details");
		}
		/*Persist*/
		user = getUser(userId);
		room = roomOptional.get();
		Long totalPrice = noOfDaysBetween * room.getPrice();
		Long amountToBePaid = totalPrice;
		Long bonusAmount = user.getBonuspoints().getBonusPoint();
		if(roomDetails.isUseBonus()) {
			Long remainingBonusPoints = (long) 0;
			if(bonusAmount >= totalPrice) {
				remainingBonusPoints = user.getBonuspoints().getBonusPoint() - room.getPrice();
				amountToBePaid = (long)0;
				status = BookingStatus.CONFIRMED;
			}else {
				amountToBePaid = totalPrice - user.getBonuspoints().getBonusPoint();
			}
			user.getBonuspoints().setBonusPoint(remainingBonusPoints);
			userBonusRepository.save(user.getBonuspoints());
		}
		bookingDetails = bookingDetailsRepository.save(new BookingDetails(checkInDate,checkOutDate,amountToBePaid,totalPrice,noOfDaysBetween,roomDetails.isUseBonus(), status, user, room, bonusAmount));
		return bookingDetails;
	}
	
	public User getUser(Long userId) throws Exception {
		Optional<User> user = userRepository.findById(userId);
		if(user.isPresent()) {
				return user.get();
		}else {
			throw new Exception("Invalid User");
		}
				
	}
	

}
