package com.bookmymovie.pojo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import lombok.Data;

@Data
@Entity
public class BookingDetails {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int bid;
	private String seatnumber;
	private String paymentdetails;
	
	@OneToOne
	@JoinColumn (name = "tid_bid")
	TheaterDetails theaterDetails;
	
	@OneToOne
	@JoinColumn(name = "sid_bid")
	ScreenDetails screenDetails;
	
	@OneToOne
	@JoinColumn(name = "uid_bid")
	UserDetails userDetails;
	
	@OneToOne
	@JoinColumn(name = "mid_bid")
	MovieAllocation movieAllocation;

}
