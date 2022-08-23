package com.bookmymovie.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bookmymovie.pojo.TheaterDetails;
import com.bookmymovie.repo.TheatedetailsRepo;

@Service
public class TheatorDao {
	
	@Autowired
	TheatedetailsRepo theatedetailsRepo;

	public TheaterDetails insertTheatordetails(TheaterDetails theaterDetails) {
		TheaterDetails theaterDetails2 = theatedetailsRepo.save(theaterDetails);
		return theaterDetails2;
	}

	public TheaterDetails updateTheaterDetails(TheaterDetails theaterDetails) {
	TheaterDetails theaterDetails2 = theatedetailsRepo.findById(theaterDetails.getTId()).orElse(null);
	//theaterDetails2.setNoofSeat(theaterDetails.getNoofSeat());
	theaterDetails2.setTName(theaterDetails.getTName());
	return theatedetailsRepo.save(theaterDetails2);
	}

	public TheaterDetails getTheaterDetails(Integer tId) {

		return theatedetailsRepo.findById(tId).get();
	}
}
