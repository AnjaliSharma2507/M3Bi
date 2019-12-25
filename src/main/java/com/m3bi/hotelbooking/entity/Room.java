package com.m3bi.hotelbooking.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.m3bi.hotelbooking.utility.RandomNumber;

@Document
public class Room {
	@Id
    private String id;
	
    private long roomId;
    private String name;
    private String roomDetails;
    private Long price;
            
   
	public Room() {
	}
	
	public Room(Room room) {
		this.roomId = room.roomId;
		this.roomDetails = room.roomDetails;
		this.name = room.name;
		this.price = room.price;

	}
	
	public Room(String name, String roomDetails, Long price) {
        this.roomId = RandomNumber.getRandomNumber();
		this.name = name;
		this.roomDetails = roomDetails;
		this.price = price;
	}
	
	public String getName() {
		return name;
	}
	public String getRoomDetails() {
		return roomDetails;
	}
	public Long getPrice() {
		return price;
	}

	public String getId() {
		return id;
	}

	public long getRoomId() {
		return roomId;
	}

	public void setRoomId(long roomId) {
		this.roomId = roomId;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setRoomDetails(String roomDetails) {
		this.roomDetails = roomDetails;
	}

	public void setPrice(Long price) {
		this.price = price;
	}

	public void setId(String id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "Room [id=" + id + ", roomId=" + roomId + ", name=" + name + ", roomDetails=" + roomDetails + ", price="
				+ price + "]";
	}
     


}
