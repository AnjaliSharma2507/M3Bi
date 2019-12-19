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
	public List<RoomDetails> getAllRooms(Long userId) throws CustomException {
		List<Room> rooms = (List<Room>)roomRepository.findAll();
		List<RoomDetails> roomDetails;
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
	public BookingDetails bookARoom(RequestRoomDetails roomDetails, Long userId) throws CustomException {
		BookingDetails bookingDetails = new BookingDetails();
		Optional<Room> roomOptional = null;
		Room room = null;
		User user = null;
		BookingStatus status = BookingStatus.PENDING_APPROVAL;
		/*Validations*/
		if(userId == null) {
			throw  new CustomException("Please Login to book a room");
		}
	    if(roomDetails.getCheckInDate() == null || roomDetails.getCheckInDate().isEmpty()
	    || roomDetails.getCheckOutDate()== null || roomDetails.getCheckOutDate().isEmpty()||roomDetails.getRoom() == null){
	    	throw new CustomException("Please provide checkin, checkout and room details.");
	    }
		LocalDate checkInDate = LocalDate.parse(roomDetails.getCheckInDate());
		LocalDate checkOutDate = LocalDate.parse(roomDetails.getCheckOutDate());
		long noOfDaysBetween = ChronoUnit.DAYS.between(checkInDate, checkOutDate);
		if(noOfDaysBetween < 1) {
			throw new CustomException("Invalid Checkin And Checkout dates");
		}
		roomOptional = roomRepository.findById(roomDetails.getRoom());
		if(!roomOptional.isPresent()) {
			throw new CustomException("Invalid Room Details");
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
				remainingBonusPoints = bonusAmount - totalPrice;
				amountToBePaid = (long)0;
				status = BookingStatus.CONFIRMED;
				user.getBonuspoints().setBonusPoint(remainingBonusPoints);
				userBonusRepository.save(user.getBonuspoints());
			}else {
				amountToBePaid = totalPrice - user.getBonuspoints().getBonusPoint();
			}
		}
		bookingDetails = bookingDetailsRepository.save(new BookingDetails(checkInDate,checkOutDate,amountToBePaid,totalPrice,noOfDaysBetween,roomDetails.isUseBonus(), status, user, room, bonusAmount));
		return bookingDetails;
	}
	
	public User getUser(Long userId) throws CustomException {
		Optional<User> user = userRepository.findById(userId);
		if(user.isPresent()) {
				return user.get();
		}else {
			throw new CustomException("Invalid User");
		}
				
	}
	
	public BookingDetails getBookingDetails(Long bookingId) throws CustomException {
		Optional<BookingDetails> bookingDetails = bookingDetailsRepository.findById(bookingId);
		if(bookingDetails.isPresent()) {
				return bookingDetails.get();
		}else {
			throw new CustomException("No Room Booking Found");
		}
				
	}


	@Override
	public BookingDetails confirmBooking(Long userId, Long bookingId) throws CustomException {
		User user = getUser(userId);
		BookingDetails bookingDetails = getBookingDetails(bookingId);
		
		if(bookingDetails.getStatus().equals(BookingStatus.CONFIRMED)) {
			throw new CustomException("Booking is already Confirmed.");
		}
		
		if(bookingDetails.getStatus().equals(BookingStatus.PENDING_APPROVAL)) {
			if(bookingDetails.isBonusApplied()) {
				user.getBonuspoints().setBonusPoint(bookingDetails.getTotalPrice()-bookingDetails.getBonusAmount());
				userBonusRepository.save(user.getBonuspoints());
			}
			bookingDetails.setStatus(BookingStatus.CONFIRMED);
			bookingDetails = bookingDetailsRepository.save(bookingDetails);
		}else {
			throw new CustomException("Booking can't be confirmed as it is either already confirmed or canceled by the user.");
		}
		return bookingDetails;
	}

	@Override
	public BookingDetails cancelBooking(Long userId, Long bookingId) throws CustomException {
		User user = getUser(userId);
		BookingDetails bookingDetails = getBookingDetails(bookingId);
		LocalDate today = LocalDate.now();
		if(today.isAfter(bookingDetails.getCheckInDate())) {
			throw new CustomException("Booking cannot be canceled.");
		}
		
		if(bookingDetails.getStatus().equals(BookingStatus.CANCELED)) {
			throw new CustomException("Booking is already canceled.");
		}
		
		if(bookingDetails.getStatus().equals(BookingStatus.CONFIRMED)) {
			if(bookingDetails.isBonusApplied()) {
				user.getBonuspoints().setBonusPoint(bookingDetails.getTotalPrice() - bookingDetails.getAmountToBePaid());
				userBonusRepository.save(user.getBonuspoints());
			}
		}
		bookingDetails.setStatus(BookingStatus.CANCELED);
		bookingDetails = bookingDetailsRepository.save(bookingDetails);
		return bookingDetails;
	}
	

}
