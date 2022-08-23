package com.bookmymovie.usercontroller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bookmymovie.dao.UserDetailsDao;
import com.bookmymovie.pojo.UserDetails;

@RestController
//@CrossOrigin("http://localhost:8081/")
@CrossOrigin("http://localhost:4200/")
@RequestMapping(value = "/api/user/")
public class UserController {

	@Autowired
	UserDetailsDao userDetailsDao;
	
@PostMapping("/")
public ResponseEntity<UserDetails> insertUser(@Validated @RequestBody UserDetails userDetails)
{
	UserDetails userDetails2 = userDetailsDao.insertUser(userDetails);
	if(userDetails2!=null)
	{
		return new ResponseEntity<UserDetails>(userDetails2, HttpStatus.CREATED);
	}
	else 
	{
		return new ResponseEntity<UserDetails>(userDetails2, HttpStatus.INTERNAL_SERVER_ERROR);
	}	
}
	
@GetMapping("/{uid}")
public List<UserDetails> getUserbyId(@Validated @PathVariable int uid)
{
	System.out.println("uid----->"+uid);
	UserDetails userDetails2 = userDetailsDao.getUserbyId(uid);
	List<UserDetails> userDetails = new ArrayList<UserDetails>();
	userDetails.add(userDetails2);
	System.out.println("userDetails2----->"+userDetails2);
	if(userDetails2!=null)
	{
		return userDetails;
	}
	else 
	{
		return userDetails;
	}	
}
@GetMapping("/")
public List<UserDetails> getAllUser(){
	return userDetailsDao.getAllUser();
}

@GetMapping("/{uname}/{password}")
public ResponseEntity<List<UserDetails>> getUserbyName(@Validated @PathVariable String uname,@PathVariable String password)
{
	System.out.println("uname----->"+uname+"password----->"+password);
	UserDetails userDetails2 = userDetailsDao.getUserbyName(uname,password);
	List<UserDetails> userDetails = new ArrayList<UserDetails>();
	System.out.println("userDetails2----->"+userDetails2+"-----size------"+userDetails.size());
	
	
	if(userDetails2!=null)
	{
		userDetails.add(userDetails2);
		System.out.println("userDetails2----!null---->"+userDetails2+"-----size------"+userDetails.size());
		return new ResponseEntity<List<UserDetails>>(userDetails, HttpStatus.OK);
	}
	else 
	{
		userDetails2 = new UserDetails();
		userDetails2.setUname("null");
		userDetails.add(userDetails2);
		System.out.println("userDetails2----null---->"+userDetails2+"-----size------"+userDetails.size());
		return  new ResponseEntity<List<UserDetails>>(userDetails, HttpStatus.OK);
	}	
}

@DeleteMapping("/{uid}")
public List<UserDetails> deleteByid(@Validated @PathVariable int uid)
{
	boolean result = userDetailsDao.deleteByID(uid);
	
	
	if(result == true)
	{
		return userDetailsDao.getAllUser();
	}
	else 
	{
		return userDetailsDao.getAllUser();
	}	
}

@PutMapping("/{uid}")
public ResponseEntity<UserDetails> updateUser(@Validated @PathVariable int uid, @RequestBody UserDetails userDetails)
{
	UserDetails userDetails2 = userDetailsDao.updateUserbyId(uid,userDetails);
	if(userDetails2!=null)
	{
		return new ResponseEntity<UserDetails>(userDetails2, HttpStatus.OK);
	}
	else 
	{
		return new ResponseEntity<UserDetails>(userDetails2, HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
}
