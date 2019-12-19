package com.m3bi.hotelbooking.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.m3bi.hotelbooking.entity.BookingDetails;
import com.m3bi.hotelbooking.entity.Room;
import com.m3bi.hotelbooking.model.RequestRoomDetails;
import com.m3bi.hotelbooking.model.RoomDetails;
import com.m3bi.hotelbooking.service.RoomService;

@RestController
@RequestMapping("/room")
public class RoomController {
	
	@Autowired
	private RoomService roomService;
	
	@GetMapping
	@ResponseBody
	public List<RoomDetails> getRooms(@RequestHeader("id") Long userid) throws Exception{
		return roomService.getAllRooms(userid);
	}
	
	@PostMapping("/book")
	@ResponseBody
	public BookingDetails bookARoom(@RequestHeader("id") Long userid, @RequestBody RequestRoomDetails requestRoomDetails) throws Exception{
		return roomService.bookARoom(requestRoomDetails, userid);
	}
	

}
