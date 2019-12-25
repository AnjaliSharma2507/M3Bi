package com.m3bi.hotelbooking.entity;


import com.m3bi.hotelbooking.utility.RandomNumber;


public class UserBonus {

	private long id;
	
	private Long bonusPoint;
	
	
	public UserBonus() {
		this.bonusPoint = null;
	}

	public UserBonus(Long bonusPoint) {
		this.id = RandomNumber.getRandomNumber();
		this.bonusPoint = bonusPoint;
	}

	public long getId() {
		return id;
	}

	public Long getBonusPoint() {
		return bonusPoint;
	}	

	public void setBonusPoint(Long bonusPoint) {
		this.bonusPoint = bonusPoint;
	}

	@Override
	public String toString() {
		return "UserBonus [id=" + id + ", bonusPoint=" + bonusPoint+"]";
	}
	
}
