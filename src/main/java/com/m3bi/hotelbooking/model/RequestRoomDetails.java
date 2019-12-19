package com.m3bi.hotelbooking.model;

public class RequestRoomDetails {
	
	private String checkInDate;
	private String checkOutDate;
	private Long room;
	private Boolean useBonus;

	public RequestRoomDetails() {
		
	}
	public RequestRoomDetails(String checkInDate, String checkOutDate, Long room, Boolean useBonus) {
		super();
		this.checkInDate = checkInDate;
		this.checkOutDate = checkOutDate;
		this.room = room;
		this.useBonus = useBonus;
	}
	public String getCheckInDate() {
		return checkInDate;
	}
	public void setCheckInDate(String checkInDate) {
		this.checkInDate = checkInDate;
	}
	public String getCheckOutDate() {
		return checkOutDate;
	}
	public void setCheckOutDate(String checkOutDate) {
		this.checkOutDate = checkOutDate;
	}
	public Long getRoom() {
		return room;
	}
	public void setRoom(Long room) {
		this.room = room;
	}
	public Boolean isUseBonus() {
		return useBonus;
	}
	public void setUseBonus(Boolean useBonus) {
		this.useBonus = useBonus;
	}
}
