package com.m3bi.hotelbooking.entity;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
public class User {
	
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private  String name;
    private  String email;
    private  String password;
    
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "bonus_id", referencedColumnName = "id")
    private UserBonus bonuspoints;
    
    
    public User() {
    	this.password = "";
		this.name = "";
		this.email = "";
    }
    
	public User(String name, String email, String password) {
		this.password = password;
		this.name = name;
		this.email = email;
	}
	
    
    
    
    
    public UserBonus getBonuspoints() {
		return bonuspoints;
	}

	public void setBonuspoints(UserBonus bonuspoints) {
		this.bonuspoints = bonuspoints;
	}
	
	public String getPassword() {
		return password;
	}

	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public String getEmail() {
		return email;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", email=" + email + "]";
	}
  

}