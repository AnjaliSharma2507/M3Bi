package com.m3bi.hotelbooking.controller;

import java.util.List;

import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.m3bi.hotelbooking.exception.CustomException;
import com.m3bi.hotelbooking.model.CustomResponse;
import com.m3bi.hotelbooking.model.RequestRoomDetails;
import com.m3bi.hotelbooking.service.RoomService;

@RestController
@RequestMapping("/room")
public class RoomController {
	
	@Autowired
	private RoomService roomService;
	
	@GetMapping
	@ResponseBody
	public CustomResponse getRooms(@RequestHeader(value = "id", required = false) Long userid) throws CustomException{
		
		return new CustomResponse(true, null, roomService.getAllRooms(userid));
	}

}
