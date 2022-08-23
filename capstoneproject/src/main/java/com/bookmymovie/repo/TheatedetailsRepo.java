package com.bookmymovie.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bookmymovie.pojo.TheaterDetails;

@Repository
public interface TheatedetailsRepo extends JpaRepository<TheaterDetails, Integer>{

}
