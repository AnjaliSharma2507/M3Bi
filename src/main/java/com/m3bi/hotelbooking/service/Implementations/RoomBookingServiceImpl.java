package com.m3bi.hotelbooking.service.Implementations;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.m3bi.hotelbooking.entity.BookingDetails;
import com.m3bi.hotelbooking.entity.Room;
import com.m3bi.hotelbooking.entity.User;
import com.m3bi.hotelbooking.exception.CustomException;
import com.m3bi.hotelbooking.model.BookingStatus;
import com.m3bi.hotelbooking.model.RequestRoomDetails;
import com.m3bi.hotelbooking.repository.BookingDetailsRepository;
import com.m3bi.hotelbooking.repository.RoomRepository;
import com.m3bi.hotelbooking.repository.UserRepository;
import com.m3bi.hotelbooking.service.RoomBookingService;
import com.m3bi.hotelbooking.service.UserService;

@Service
public class RoomBookingServiceImpl implements RoomBookingService {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private BookingDetailsRepository bookingDetailsRepository;

	
	@Autowired
	private RoomRepository roomRepository;
	
	@Autowired
	private UserService userService;
	
	@Override
	public BookingDetails bookARoom(RequestRoomDetails roomDetails, String userId) throws CustomException {
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
		if(noOfDaysBetween < 1 || checkInDate.isAfter(checkOutDate) || checkInDate.isBefore(LocalDate.now())) {
			throw new CustomException("Invalid Checkin And Checkout dates");
		}
		roomOptional = roomRepository.findByRoomId(roomDetails.getRoom());
		if(!roomOptional.isPresent()) {
			throw new CustomException("Invalid Room Details");
		}
		/*Persist*/
		user = userService.getUser(userId);
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
				user = userRepository.save(user);
			}else {
				amountToBePaid = totalPrice - user.getBonuspoints().getBonusPoint();
			}
		}
		bookingDetails = bookingDetailsRepository.save(new BookingDetails(checkInDate.toString(),checkOutDate.toString(),amountToBePaid,totalPrice,noOfDaysBetween,roomDetails.isUseBonus(), status, user, room, bonusAmount));
		return bookingDetails;
	}
	
	
	
	@Override
	public BookingDetails getBookingDetails(Long bookingId, String userId) throws CustomException {
		Optional<BookingDetails> bookingDetails = bookingDetailsRepository.findByBookingId(bookingId);
		if(bookingDetails.isPresent()) {
			    if(bookingDetails.get().getUser() != null && bookingDetails.get().getUser().getId().equals(userId)) {
			    	return bookingDetails.get();
			    }else {
			    	throw new CustomException("No Room Booking Found");
			    }
				
		}else {
			throw new CustomException("No Room Booking Found");
		}
				
	}


	@Override
	public BookingDetails confirmBooking(String userId,  Long bookingId) throws CustomException {
		User user = userService.getUser(userId);
		BookingDetails bookingDetails = getBookingDetails(bookingId, userId);
		
		if(bookingDetails.getStatus().equals(BookingStatus.CONFIRMED)) {
			throw new CustomException("Booking is already Confirmed.");
		}
		
		if(bookingDetails.getStatus().equals(BookingStatus.PENDING_APPROVAL)) {
			if(bookingDetails.isBonusApplied()) {
				if(user.getBonuspoints().getBonusPoint() <= bookingDetails.getTotalPrice()) {
				bookingDetails.setAmountToBePaid(bookingDetails.getTotalPrice()-user.getBonuspoints().getBonusPoint());
				bookingDetails.setBonusAmount(user.getBonuspoints().getBonusPoint());
				user.getBonuspoints().setBonusPoint((long)0);
				}else {
					bookingDetails.setAmountToBePaid((long)0);
					user.getBonuspoints().setBonusPoint(bookingDetails.getBonusAmount() - bookingDetails.getTotalPrice());
					bookingDetails.setBonusAmount(bookingDetails.getTotalPrice());
				}
				user = userRepository.save(user);
				
			}
			bookingDetails.setStatus(BookingStatus.CONFIRMED);
			bookingDetails = bookingDetailsRepository.save(bookingDetails);
		}else {
			throw new CustomException("Booking can't be confirmed as it is either already confirmed or canceled by the user.");
		}
		return bookingDetails;
	}

	@Override
	public BookingDetails cancelBooking(String userId, Long bookingId) throws CustomException {
		User user = userService.getUser(userId);
		BookingDetails bookingDetails = getBookingDetails(bookingId, userId);
		LocalDate today = LocalDate.now();
		LocalDate checkInDate = LocalDate.parse(bookingDetails.getCheckInDate());
		if(today.isAfter(checkInDate)) {
			throw new CustomException("Booking cannot be canceled.");
		}
		
		if(bookingDetails.getStatus().equals(BookingStatus.CANCELED)) {
			throw new CustomException("Booking is already canceled.");
		}
		
		if(bookingDetails.getStatus().equals(BookingStatus.CONFIRMED)) {
			if(bookingDetails.isBonusApplied()) {
				long retriveBonus =bookingDetails.getTotalPrice() - bookingDetails.getAmountToBePaid();
				long availableBonus = user.getBonuspoints().getBonusPoint();
				user.getBonuspoints().setBonusPoint(availableBonus+retriveBonus);
				user= userRepository.save(user);
			}
		}
		bookingDetails.setStatus(BookingStatus.CANCELED);
		bookingDetails = bookingDetailsRepository.save(bookingDetails);
		return bookingDetails;
	}



	@Override
	public Set<BookingDetails> getAllBooking(String userId) throws CustomException {
		return bookingDetailsRepository.findByUser(userId);
	   		
	}
	

}
