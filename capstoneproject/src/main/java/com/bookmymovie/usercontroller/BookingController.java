package com.bookmymovie.usercontroller;

import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bookmymovie.dao.BookingDetailsDao;
import com.bookmymovie.dao.MovieAllocationDao;
import com.bookmymovie.dao.ScreenDetailsDao;
import com.bookmymovie.dao.TheatorDao;
import com.bookmymovie.dao.UserDetailsDao;
import com.bookmymovie.pojo.BookingDetails;
import com.bookmymovie.pojo.MovieAllocation;
import com.bookmymovie.pojo.ScreenDetails;
import com.bookmymovie.pojo.TheaterDetails;
import com.bookmymovie.pojo.UserDetails;

@RestController
@CrossOrigin("http://localhost:4200/")
@RequestMapping(value = "/api/user/")
public class BookingController {
	
	@Autowired
	BookingDetailsDao bookingdetaildao; 
	
	@Autowired
	TheatorDao theaterdao;
	
	@Autowired
	ScreenDetailsDao screendetailsdao;
	
	@Autowired
	UserDetailsDao userdetailsdao;
	
	@Autowired
	MovieAllocationDao movieAllocationDao;
	
	Logger logger = Logger.getAnonymousLogger();
	
	@PostMapping(value = "inserbooking")
	public ResponseEntity<BookingDetails> insertBooking(@RequestBody BookingDetails bookingDetails)
	{
		logger.info("bookingDetails------>"+bookingDetails);
		TheaterDetails theaterDetails = theaterdao.getTheaterDetails(bookingDetails.getTheaterDetails().getTId());
		bookingDetails.setTheaterDetails(theaterDetails);
		ScreenDetails screenDetails = screendetailsdao.getScreenDetails(bookingDetails.getScreenDetails());
		bookingDetails.setScreenDetails(screenDetails);
		UserDetails userDetails = userdetailsdao.getUserbyId(bookingDetails.getUserDetails().getUserid());
		bookingDetails.setUserDetails(userDetails);
		MovieAllocation allocation = movieAllocationDao.getMoviebyid(bookingDetails.getMovieAllocation().getMovieId());
		bookingDetails.setMovieAllocation(allocation);
		BookingDetails bookingDetails2 = bookingdetaildao.insertBookingDetails(bookingDetails);
		if(bookingDetails2!=null)
		{
			return new ResponseEntity<BookingDetails>(bookingDetails2, HttpStatus.CREATED);
		}
		else
		{
			return new ResponseEntity<BookingDetails>(bookingDetails2, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}

	
	@GetMapping(value = "getbooking/{bid}")
	public ResponseEntity<BookingDetails> getBookingDetails(@PathVariable int bid)
	{
		BookingDetails bookingDetails = bookingdetaildao.getByid(bid);
		if(bookingDetails!=null)
		{
			return new ResponseEntity<BookingDetails>(bookingDetails, HttpStatus.OK);
		}
		else
		{
			return new ResponseEntity<BookingDetails>(bookingDetails, HttpStatus.NOT_FOUND);
		}
		
	}
}
