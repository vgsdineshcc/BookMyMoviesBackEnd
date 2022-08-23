package com.bookmymovie.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bookmymovie.pojo.MovieAllocation;

@Repository
public interface MovieAllocationRepo extends JpaRepository<MovieAllocation, Integer>{

}
