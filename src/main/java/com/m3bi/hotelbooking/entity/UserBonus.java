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
	
	private final Long bonusPoint;
	
	@OneToOne(mappedBy = "bonus")
    private User user;

	public UserBonus(long id, Long bonusPoint, User user) {
		super();
		this.id = id;
		this.bonusPoint = bonusPoint;
		this.user = user;
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

	@Override
	public String toString() {
		return "UserBonus [id=" + id + ", bonusPoint=" + bonusPoint + ", user=" + user + "]";
	}
	
}
