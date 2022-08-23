package com.bookmymovie.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bookmymovie.pojo.BookingDetails;
import com.bookmymovie.repo.BookingDetailsRepo;

@Service
public class BookingDetailsDao {
	
@Autowired
BookingDetailsRepo bookingDetailsRepo;

public BookingDetails insertBookingDetails(BookingDetails bookingDetails) {

	return bookingDetailsRepo.save(bookingDetails);
}

public BookingDetails getByid(int bid) {
	return bookingDetailsRepo.findById(bid).get();
}

}
