package com.bookmymovie.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bookmymovie.pojo.UserDetails;
import com.bookmymovie.repo.UserDetailsRepo;

@Service
public class UserDetailsDao {
	
	@Autowired
	UserDetailsRepo userDetailsRepo;

	public UserDetails insertUser(UserDetails userDetails) {
		return userDetailsRepo.save(userDetails);
	}

	public UserDetails getUserbyId(int uid) {

		if(userDetailsRepo.findById(uid).isPresent())
		{
			return userDetailsRepo.findById(uid).get();
		}
		else 
		{
			return null;
		}
		
		
	}

	public boolean deleteByID(int uid) {

		if(userDetailsRepo.findById(uid).isPresent())
		{
			userDetailsRepo.deleteById(uid);
			return true;
		}
		else 
		{
			return false;
		}
	}

	public UserDetails updateUserbyId(int uid, UserDetails userDetails) {
		if(userDetailsRepo.findById(uid).isPresent())
		{
			UserDetails userDetails2 = userDetailsRepo.findById(uid).get();
			userDetails2.setEmail(userDetails.getEmail());
			userDetails2.setGender(userDetails.getGender());
			userDetails2.setUname(userDetails.getUname());
			userDetails2.setPassword(userDetails.getPassword());
			return userDetailsRepo.save(userDetails2);
		}
		else 
		{
			return null;
		}
	}
	
	public List<UserDetails> getAllUser(){
		return userDetailsRepo.findAll();
	}

	public UserDetails getUserbyName(String uname, String password) {

		return userDetailsRepo.findByEmailAddress(uname, password);
	}

}
