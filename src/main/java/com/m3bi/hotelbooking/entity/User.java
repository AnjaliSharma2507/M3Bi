package com.m3bi.hotelbooking.entity;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class User {	
	
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private  String name;
    private  String username;
    private  String email;
    
    @JsonIgnore
    private  String password;
    

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "bonus_id", referencedColumnName = "id")
    private UserBonus bonuspoints;
    
    @JsonIgnore
	@OneToMany(cascade= CascadeType.ALL)
	@JoinColumn(name = "booking_id", referencedColumnName = "id")
    private Set<BookingDetails> bookingDetails;
    
    
    public User() {
    }
    
	public User(String name, String email, String password) {
		this.password = password;
		this.name = name;
		this.email = email;
		this.username = email;
	}
	
    
	public User(User user) {
        this.id = user.id;
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
	
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
	
	 

	public Set<BookingDetails> getBookingDetails() {
		return bookingDetails;
	}

	public void setBookingDetails(Set<BookingDetails> bookingDetails) {
		this.bookingDetails = bookingDetails;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", username=" + username + ", email=" + email + ", bonuspoints="
				+ bonuspoints+"]";
	}
  

}