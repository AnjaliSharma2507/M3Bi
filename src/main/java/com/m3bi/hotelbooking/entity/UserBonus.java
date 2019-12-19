package com.m3bi.hotelbooking.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
public class UserBonus {
	
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	private Long bonusPoint;
	
	@OneToOne(mappedBy = "bonuspoints")
    private User user;

	
	public UserBonus() {
		this.bonusPoint = null;
	}

	public UserBonus(Long bonusPoint) {
		this.bonusPoint = bonusPoint;
	}

	public long getId() {
		return id;
	}

	public Long getBonusPoint() {
		return bonusPoint;
	}

	public User getUser() {
		return user;
	}
	
	

	public void setBonusPoint(Long bonusPoint) {
		this.bonusPoint = bonusPoint;
	}

	@Override
	public String toString() {
		return "UserBonus [id=" + id + ", bonusPoint=" + bonusPoint + ", user=" + user + "]";
	}
	
}
