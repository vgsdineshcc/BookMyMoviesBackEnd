package com.bookmymovie.pojo;

import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

@Data
@Entity
public class MovieAllocation {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer movieId;
	private String movieName;
	private Long movieCost;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	private Date movieDT;
	private String timeSlots;
	private String moviePath;
	private String cast;
	private String language;
	private String available;
	
	@OneToOne
	@JoinColumn(name = "mid_tid")
	TheaterDetails theaterDetails;
	
	@OneToOne
	@JoinColumn(name = "mid_sid")
	ScreenDetails screenDetails;

}
