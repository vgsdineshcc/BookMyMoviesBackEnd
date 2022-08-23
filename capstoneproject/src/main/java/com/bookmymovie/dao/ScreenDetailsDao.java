package com.bookmymovie.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bookmymovie.pojo.ScreenDetails;
import com.bookmymovie.pojo.TheaterDetails;
import com.bookmymovie.repo.ScreenDetailsRepo;

@Service
public class ScreenDetailsDao {
	
	@Autowired
	ScreenDetailsRepo screenDetailsRepo;

	public ScreenDetails insertScreenDetails(ScreenDetails screenDetails, TheaterDetails theaterDetails) {
		//ScreenDetails screenDetails2 = screenDetails;
		screenDetails.setTheaterDetails(theaterDetails);
		return screenDetailsRepo.save(screenDetails);
	}

	public ScreenDetails getScreenDetails(ScreenDetails screenDetails) {
		// TODO Auto-generated method stub
		return screenDetailsRepo.findById(screenDetails.getSid()).get();
	}

}
