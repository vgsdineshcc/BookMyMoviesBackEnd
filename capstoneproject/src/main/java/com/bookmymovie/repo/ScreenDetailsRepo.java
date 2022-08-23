package com.bookmymovie.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bookmymovie.pojo.ScreenDetails;

@Repository
public interface ScreenDetailsRepo extends JpaRepository<ScreenDetails, Integer>{

}
