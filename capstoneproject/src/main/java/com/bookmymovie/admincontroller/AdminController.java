package com.bookmymovie.admincontroller;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Logger;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.bookmymovie.dao.MovieAllocationDao;
import com.bookmymovie.dao.ScreenDetailsDao;
import com.bookmymovie.dao.TheatorDao;
import com.bookmymovie.pojo.FileUtil;
import com.bookmymovie.pojo.MovieAllocation;
import com.bookmymovie.pojo.ScreenDetails;
import com.bookmymovie.pojo.TheaterDetails;

@CrossOrigin(origins = "http://localhost:4200", allowedHeaders = "*")  
@RequestMapping(value = "/api/admin/")
@RestController
public class AdminController {
	
@Autowired	
MovieAllocationDao movieAllocationDao;

@Autowired
TheatorDao theatorDao;

@Autowired
ScreenDetailsDao screenDetailsDao;


Logger logger = Logger.getAnonymousLogger();


@PostMapping(value="saveMoview")
public ResponseEntity<Integer> insertMovie(@RequestBody MovieAllocation ma)
{
	logger.info("ma.getTheaterDetails()-->"+ma.getTheaterDetails()+"--->"+ma);
	TheaterDetails theaterDetails = theatorDao.insertTheatordetails(ma.getTheaterDetails());
	ScreenDetails screenDetails = screenDetailsDao.insertScreenDetails(ma.getScreenDetails(),theaterDetails);
	MovieAllocation movieAllocation = new MovieAllocation();
	movieAllocation.setMovieCost(ma.getMovieCost());
	movieAllocation.setMovieName(ma.getMovieName());
	movieAllocation.setMoviePath(ma.getMoviePath());
	movieAllocation.setMovieDT(ma.getMovieDT());
	movieAllocation.setTheaterDetails(theaterDetails);
	movieAllocation.setScreenDetails(screenDetails);
	movieAllocation.setCast(ma.getCast());
	movieAllocation.setAvailable(ma.getAvailable());
	movieAllocation.setLanguage(ma.getLanguage());
	movieAllocation.setTimeSlots(ma.getTimeSlots());	
	MovieAllocation movieAllocation2 = movieAllocationDao.insertMoviedetails(movieAllocation);
	if(movieAllocation2!=null)
		return new ResponseEntity<Integer>(movieAllocation2.getMovieId(),HttpStatus.CREATED);
	else
		return new ResponseEntity<Integer>(movieAllocation2.getMovieId(),HttpStatus.INTERNAL_SERVER_ERROR);
}

@PostMapping(value="/uploadImage/{userId}", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
public ResponseEntity<Integer> handleFileUpload(@PathVariable int userId , @RequestParam("file") MultipartFile files, HttpSession session) {  
	List<MovieAllocation> listMovie = new ArrayList<MovieAllocation>();
	try {
           createDirIfNotExist();
           logger.info("--------->"+userId);
           List<String> fileNames = new ArrayList<>();
           MovieAllocation movieAllocation = movieAllocationDao.getMoviebyid(userId);
           Arrays.asList(files).stream().forEach(file -> {
               byte[] bytes = new byte[0];
               try {
                   bytes = file.getBytes();
                   Path path=Paths.get(FileUtil.folderPath + file.getOriginalFilename());
                   Files.write(path, bytes);
                   Files.move(path, path.resolveSibling(
                           movieAllocation.getMovieId()+"_"+file.getOriginalFilename()));
                   logger.info("-----path.toString()----->+"+path.toString());
                   String newPath = FileUtil.folderPath+userId+"_"+file.getOriginalFilename();
                   movieAllocation.setMoviePath(newPath);
                   movieAllocationDao.updateMoviedetails(userId, movieAllocation);
                   logger.info("-----path.toString()----->+"+newPath);
                   fileNames.add(path.toString());
               } catch (IOException e) {
               	e.printStackTrace();
               }
           }); 
           return new ResponseEntity<Integer>(userId, HttpStatus.CREATED);
       } catch (Exception e) {
    	   return new ResponseEntity<Integer>(0, HttpStatus.EXPECTATION_FAILED);
       } 
}  

/**
 * Create directory to save files, if not exist
 */
private void createDirIfNotExist() {
    //create directory to save the files
    File directory = new File(FileUtil.folderPath);
    if (! directory.exists()){
        directory.mkdir();
    }
}

@GetMapping(value = "getmovie")
public List<MovieAllocation> getMovie()
{
	List<MovieAllocation> movieAllocations = movieAllocationDao.getAllmovie();
	
	movieAllocations.forEach(action -> logger.info(action.getMovieName()+" Theater Details-->"+action.getTheaterDetails()));
	
	return movieAllocations;
}

@GetMapping(value = "getmovie/{mid}")
public List<MovieAllocation> getmoviename(@PathVariable int mid)
{
	logger.info("----mid----->"+mid);
	MovieAllocation movieAllocation = movieAllocationDao.getMoviebyid(mid);
	//Path path = Paths.get(movieAllocation.getMoviePath());
	//movieAllocation.setMoviePath("../assets/images/"+path.getFileName().toString());
	logger.info("----movieAllocation----->"+movieAllocation);
	List<MovieAllocation> movieAllocations=new ArrayList<MovieAllocation>();	
	movieAllocations.add(movieAllocation);
	return movieAllocations;
}

@PutMapping(value = "movieupdate/{mid}")
public ResponseEntity<MovieAllocation> updateMovie(@PathVariable int mid, @RequestBody MovieAllocation ma)
{
	TheaterDetails theaterDetails = theatorDao.updateTheaterDetails(ma.getTheaterDetails());
	ma.setTheaterDetails(theaterDetails);
	MovieAllocation movieAllocation = movieAllocationDao.updateMoviedetails(mid,ma);
	if(movieAllocation!=null)
		return new ResponseEntity<MovieAllocation>(movieAllocation,HttpStatus.ACCEPTED);
	else
		return new ResponseEntity<MovieAllocation>(movieAllocation,HttpStatus.INTERNAL_SERVER_ERROR);
}

@DeleteMapping(value = "moviedelete/{mid}")
public ResponseEntity<String> deleteMovie(@PathVariable int mid)
{
	boolean result = movieAllocationDao.deleteMoviedetails(mid);
	
	if(result == true)
	{
		return new ResponseEntity<String>("Deleted Successfully",HttpStatus.OK);
				
	}
	else
	{
		return new ResponseEntity<String>("Object Not Found",HttpStatus.NOT_FOUND);
	}
}

}
