package com.m3bi.hotelbooking.controller;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.m3bi.hotelbooking.entity.BookingDetails;
import com.m3bi.hotelbooking.exception.CustomException;
import com.m3bi.hotelbooking.model.CustomResponse;
import com.m3bi.hotelbooking.model.RequestRoomDetails;
import com.m3bi.hotelbooking.service.RoomBookingService;
import com.m3bi.hotelbooking.service.RoomService;

@RestController
@RequestMapping("/booking")
public class BookingController {
	
	
	@Autowired
	private RoomBookingService bookingService;
	
	@PostMapping
	@ResponseBody
	public CustomResponse<BookingDetails> bookARoom(@RequestAttribute("id") Long userid, @RequestBody RequestRoomDetails requestRoomDetails) throws CustomException{
		return new CustomResponse<BookingDetails>(true, null, bookingService.bookARoom(requestRoomDetails, userid));
	}
	
	@GetMapping("/confirm/{bookingId}")
	@ResponseBody
	public CustomResponse<BookingDetails> confirmBooking(@RequestAttribute("id") Long userId, @PathVariable("bookingId") Long bookingId) throws CustomException{
		return new CustomResponse<BookingDetails>(true, null, bookingService.confirmBooking(userId, bookingId));
		
	}
	
	@GetMapping("/cancel/{bookingId}")
	@ResponseBody
	public CustomResponse<BookingDetails> cancelBooking(@RequestAttribute("id") Long userId, @PathVariable("bookingId") Long bookingId) throws CustomException{
		return new CustomResponse<BookingDetails>(true, null, bookingService.cancelBooking(userId, bookingId));
		
	}
   	
	
	@GetMapping
	@ResponseBody
	public CustomResponse<Set<BookingDetails>> getAllBooking(@RequestHeader("id") Long userId)throws CustomException{
		return new CustomResponse<Set<BookingDetails>>(true, null, bookingService.getAllBooking(userId));
		
	}
	
	@GetMapping("{bookingId}")
	@ResponseBody
	public CustomResponse<BookingDetails> getBookingById(@RequestHeader("id") Long userId, @PathVariable("bookingId") Long bookingId) throws CustomException{
		return new CustomResponse<BookingDetails>(true, null, bookingService.getBookingDetails(bookingId, userId));
		
	}
}
