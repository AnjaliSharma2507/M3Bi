package com.m3bi.hotelbooking.entity;

import java.util.Set;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.IndexDirection;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.m3bi.hotelbooking.utility.RandomNumber;

@Document("user")
public class User {	
	
	@Id
	private String id;

    private long userId;
	private  String name;
	
	@Indexed(name = "user_name", unique = true)
    private  String username;
    private  String email;
    @JsonIgnore
    private  String password;
    private UserBonus bonuspoints;
    
    public User() {
    }
    
	public User(String name, String email, String password) {
		this.userId = RandomNumber.getRandomNumber();
		this.password = password;
		this.name = name;
		this.email = email;
		this.username = email;
	}
	
    
	public User(User user) {
        this.userId = user.userId;
        this.name = user.name;
        this.email = user.email;
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
	
	public String getName() {
		return name;
	}
	public String getEmail() {
		return email;
	}
	
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
	

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", userId=" + userId + ", name=" + name + ", username=" + username + ", email="
				+ email + ", password=" + password + ", bonuspoints=" + bonuspoints + "]";
	}
	
	
  

}