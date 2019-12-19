package com.m3bi.hotelbooking.entity;

import java.time.LocalDate;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.m3bi.hotelbooking.model.BookingStatus;

@Entity
public class BookingDetails {
	
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private LocalDate checkInDate;
	private LocalDate checkOutDate;
	private Long amountToBePaid;
	private Long totalPrice;
	private Long bonusAmount;
	private Long noOfDays;
	private Boolean isBonusApplied;
	private BookingStatus status;
	
	
	@JsonIgnore
	@ManyToOne(cascade= CascadeType.ALL)
	@JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;
	
	 
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "room_id", referencedColumnName = "id")
    private Room room;
    
	public BookingDetails(LocalDate checkInDate, LocalDate checkOutDate, Long amountToBePaid, Long totalPrice,
			Long noOfDays, Boolean isBonusApplied, BookingStatus status, User user, Room room, Long bonusAmount) {

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
	}


	public BookingDetails() {
		super();
	}
	

	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public LocalDate getCheckInDate() {
		return checkInDate;
	}


	public void setCheckInDate(LocalDate checkInDate) {
		this.checkInDate = checkInDate;
	}


	public LocalDate getCheckOutDate() {
		return checkOutDate;
	}


	public void setCheckOutDate(LocalDate checkOutDate) {
		this.checkOutDate = checkOutDate;
	}


	public Long getAmountToBePaid() {
		return amountToBePaid;
	}


	public void setAmountToBePaid(Long amountToBePaid) {
		this.amountToBePaid = amountToBePaid;
	}


	public Boolean isBonusApplied() {
		return isBonusApplied;
	}


	public void setBonusApplied(Boolean isBonusApplied) {
		this.isBonusApplied = isBonusApplied;
	}


	public User getUser() {
		return user;
	}


	public void setUser(User user) {
		this.user = user;
	}


	public Room getRoom() {
		return room;
	}


	public void setRoom(Room room) {
		this.room = room;
	}


	public BookingStatus getStatus() {
		return status;
	}


	public void setStatus(BookingStatus status) {
		this.status = status;
	}


	public Long getTotalPrice() {
		return totalPrice;
	}


	public void setTotalPrice(Long totalPrice) {
		this.totalPrice = totalPrice;
	}


	public Long getNoOfDays() {
		return noOfDays;
	}


	public void setNoOfDays(Long noOfDays) {
		this.noOfDays = noOfDays;
	}
	
	public void setIsBonusApplied(Boolean isBonusApplied) {
		this.isBonusApplied = isBonusApplied;
	}


	public Long getBonusAmount() {
		return bonusAmount;
	}


	public void setBonusAmount(Long bonusAmount) {
		this.bonusAmount = bonusAmount;
	}
    
	
}
