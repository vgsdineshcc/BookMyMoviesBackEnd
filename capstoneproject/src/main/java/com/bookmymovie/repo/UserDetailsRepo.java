package com.bookmymovie.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.bookmymovie.pojo.UserDetails;
@Repository
public interface UserDetailsRepo extends JpaRepository<UserDetails, Integer>{

	 @Query("select u from UserDetails u where u.uname = ?1 and u.password = ?2")
	  UserDetails findByEmailAddress(String uname, String password);
	}

