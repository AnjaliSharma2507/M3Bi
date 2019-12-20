package com.m3bi.hotelbooking.service;

import java.util.Set;

import com.m3bi.hotelbooking.entity.BookingDetails;
import com.m3bi.hotelbooking.exception.CustomException;
import com.m3bi.hotelbooking.model.RequestRoomDetails;

public interface RoomBookingService {

	public BookingDetails bookARoom(RequestRoomDetails roomDetails, Long userId) throws CustomException;

	public BookingDetails cancelBooking(Long userId, Long bookingId) throws CustomException;

	public BookingDetails confirmBooking(Long userId, Long bookingId) throws CustomException;
	
	public BookingDetails getBookingDetails(Long bookingId, Long userId) throws CustomException;
	
	public Set<BookingDetails> getAllBooking(Long userId) throws CustomException;

}
