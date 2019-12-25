package com.m3bi.hotelbooking.service;

import java.util.Set;

import com.m3bi.hotelbooking.entity.BookingDetails;
import com.m3bi.hotelbooking.exception.CustomException;
import com.m3bi.hotelbooking.model.RequestRoomDetails;

public interface RoomBookingService {

	public BookingDetails bookARoom(RequestRoomDetails roomDetails, String userId) throws CustomException;

	public BookingDetails cancelBooking(String userId, Long bookingId) throws CustomException;

	public BookingDetails confirmBooking(String userId, Long bookingId) throws CustomException;
	
	public BookingDetails getBookingDetails(Long bookingId, String userId) throws CustomException;
	
	public Set<BookingDetails> getAllBooking(String userId) throws CustomException;

}
