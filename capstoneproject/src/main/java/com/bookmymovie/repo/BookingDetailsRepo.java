package com.bookmymovie.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bookmymovie.pojo.BookingDetails;

@Repository
public interface BookingDetailsRepo extends JpaRepository<BookingDetails, Integer>{

}
