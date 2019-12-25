package com.m3bi.hotelbooking.entity;

import java.time.LocalDate;
import java.util.Set;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.m3bi.hotelbooking.model.BookingStatus;
import com.m3bi.hotelbooking.utility.DateTime;
import com.m3bi.hotelbooking.utility.RandomNumber;

@Document("bookingdetails")
public class BookingDetails {
	
	@Id
	private String id;
	
	private Long bookingId;
	private String checkInDate;
	private String checkOutDate;
	private Long amountToBePaid;
	private Long totalPrice;
	private Long bonusAmount;
	private Long noOfDays;
	private Boolean isBonusApplied;
	private BookingStatus status;
	private String createdOn;
	private String updatedOn;
	
	@JsonIgnore
	@DBRef
    private User user;
	
	@DBRef
    private Room room;
    
    public BookingDetails() {
		super();
	}
    
	public BookingDetails(String checkInDate, String checkOutDate, Long amountToBePaid, Long totalPrice,
			Long noOfDays, Boolean isBonusApplied, BookingStatus status, User user, Room room, Long bonusAmount) {
		this.bookingId = RandomNumber.getRandomNumber();
		this.checkInDate = checkInDate;
		this.checkOutDate = checkOutDate;
		this.amountToBePaid = amountToBePaid;
		this.totalPrice = totalPrice;
		this.noOfDays = noOfDays;
		this.isBonusApplied = isBonusApplied;
		this.status = status;
		this.user = user;
		this.room = room;
		this.bonusAmount = bonusAmount;
		this.createdOn = DateTime.getCurrentDateTime();
		this.updatedOn = DateTime.getCurrentDateTime();
		
	}
	
	public Long getBookingId() {
		return bookingId;
	}

	public void setBookingId(Long bookingId) {
		this.updatedOn = DateTime.getCurrentDateTime();
		this.bookingId = bookingId;
	}

	public String getCheckInDate() {
		return checkInDate;
	}


	public void setCheckInDate(String checkInDate) {
		this.updatedOn = DateTime.getCurrentDateTime();
		this.checkInDate = checkInDate;
	}


	public String getCheckOutDate() {
		return checkOutDate;
	}


	public void setCheckOutDate(String checkOutDate) {
		this.updatedOn = DateTime.getCurrentDateTime();
		this.checkOutDate = checkOutDate;
	}


	public Long getAmountToBePaid() {
		return amountToBePaid;
	}


	public void setAmountToBePaid(Long amountToBePaid) {
		this.updatedOn = DateTime.getCurrentDateTime();
		this.amountToBePaid = amountToBePaid;
	}


	public Boolean isBonusApplied() {
		return isBonusApplied;
	}


	public void setBonusApplied(Boolean isBonusApplied) {
		this.updatedOn = DateTime.getCurrentDateTime();
		this.isBonusApplied = isBonusApplied;
	}


	public User getUser() {
		return user;
	}


	public void setUser(User user) {
		this.updatedOn = DateTime.getCurrentDateTime();
		this.user = user;
	}


	public Room getRoom() {
		return room;
	}


	public void setRoom(Room room) {
		this.updatedOn = DateTime.getCurrentDateTime();
		this.room = room;
	}


	public BookingStatus getStatus() {
		return status;
	}


	public void setStatus(BookingStatus status) {
		this.updatedOn = DateTime.getCurrentDateTime();
		this.status = status;
	}


	public Long getTotalPrice() {
		return totalPrice;
	}


	public void setTotalPrice(Long totalPrice) {
		this.updatedOn = DateTime.getCurrentDateTime();
		this.totalPrice = totalPrice;
	}


	public Long getNoOfDays() {
		return noOfDays;
	}


	public void setNoOfDays(Long noOfDays) {
		this.updatedOn = DateTime.getCurrentDateTime();
		this.noOfDays = noOfDays;
	}
	
	public void setIsBonusApplied(Boolean isBonusApplied) {
		this.updatedOn = DateTime.getCurrentDateTime();
		this.isBonusApplied = isBonusApplied;
	}


	public Long getBonusAmount() {
		return bonusAmount;
	}


	public void setBonusAmount(Long bonusAmount) {
		this.updatedOn = DateTime.getCurrentDateTime();
		this.bonusAmount = bonusAmount;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.updatedOn = DateTime.getCurrentDateTime();
		this.id = id;
	}

	@Override
	public String toString() {
		return "BookingDetails [id=" + id + ", bookingId=" + bookingId + ", checkInDate=" + checkInDate
				+ ", checkOutDate=" + checkOutDate + ", amountToBePaid=" + amountToBePaid + ", totalPrice=" + totalPrice
				+ ", bonusAmount=" + bonusAmount + ", noOfDays=" + noOfDays + ", isBonusApplied=" + isBonusApplied
				+ ", status=" + status + ", user=" + user + ", room=" + room + "]";
	}
	
	
    
	
}
