package com.m3bi.hotelbooking.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Room {
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private final String name;
    private final String roomDetails;
    private final Long price;
	public Room(String name, String roomDetails, Long price) {
		super();
		this.name = name;
		this.roomDetails = roomDetails;
		this.price = price;
	}
	public long getId() {
		return id;
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
	@Override
	public String toString() {
		return "Room [id=" + id + ", name=" + name + ", roomDetails=" + roomDetails + ", price=" + price + "]";
	}


}
