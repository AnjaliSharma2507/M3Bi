package com.m3bi.hotelbooking.entity;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Entity
public class Room {
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String name;
    private String roomDetails;
    private Long price;
        
    
	public Room() {
		this.roomDetails = "";
		this.name = "";
		this.price = null;

	}
	
	public Room(String name, String roomDetails, Long price) {
		this.roomDetails = "";
		this.name = "";
		this.price = null;
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
