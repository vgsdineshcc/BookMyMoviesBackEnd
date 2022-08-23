package com.bookmymovie.pojo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Data
@Entity
public class ScreenDetails {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer sid;
	private String sname;
	private Long noofSeat;
	
	@OneToOne
	@JoinColumn(name = "sid_tid")
	TheaterDetails theaterDetails;
	
}
