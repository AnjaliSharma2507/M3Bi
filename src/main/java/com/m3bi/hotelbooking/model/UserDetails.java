package com.m3bi.hotelbooking.model;

public class UserDetails {
	
	public String name;
	public String email;
	public String password;
	public long bonus;
	
	
	public UserDetails() {
		super();

	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}

	public long getBonus() {
		return bonus;
	}

	public void setBonus(long bonus) {
		this.bonus = bonus;
	}
	
	
	

}
