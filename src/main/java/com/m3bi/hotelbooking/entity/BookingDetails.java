package com.m3bi.hotelbooking.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class BookingDetails {
	
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
}
