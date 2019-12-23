package com.m3bi.hotelbooking.controller;

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
	public CustomResponse bookARoom(@RequestAttribute("id") Long userid, @RequestBody RequestRoomDetails requestRoomDetails) throws CustomException{
		return new CustomResponse(true, null, bookingService.bookARoom(requestRoomDetails, userid));
	}
	
	@GetMapping("/confirm/{bookingId}")
	@ResponseBody
	public CustomResponse confirmBooking(@RequestAttribute("id") Long userId, @PathVariable("bookingId") Long bookingId) throws CustomException{
		return new CustomResponse(true, null, bookingService.confirmBooking(userId, bookingId));
		
	}
	
	@GetMapping("/cancel/{bookingId}")
	@ResponseBody
	public CustomResponse cancelBooking(@RequestAttribute("id") Long userId, @PathVariable("bookingId") Long bookingId) throws CustomException{
		return new CustomResponse(true, null, bookingService.cancelBooking(userId, bookingId));
		
	}
   	
	
	@GetMapping
	@ResponseBody
	public CustomResponse ggetAllBooking(@RequestHeader("id") Long userId)throws CustomException{
		return new CustomResponse(true, null, bookingService.getAllBooking(userId));
		
	}
	
	@GetMapping("{bookingId}")
	@ResponseBody
	public CustomResponse getBookingById(@RequestHeader("id") Long userId, @PathVariable("bookingId") Long bookingId) throws CustomException{
		return new CustomResponse(true, null, bookingService.getBookingDetails(bookingId, userId));
		
	}
}
