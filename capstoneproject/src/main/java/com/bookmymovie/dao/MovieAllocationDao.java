package com.bookmymovie.dao;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bookmymovie.pojo.MovieAllocation;
import com.bookmymovie.repo.MovieAllocationRepo;

@Service
public class MovieAllocationDao {
	
	@Autowired
   MovieAllocationRepo movieAllocationRepo;

	public MovieAllocation insertMoviedetails(MovieAllocation ma) {
		MovieAllocation movieAllocation = movieAllocationRepo.save(ma);
		return movieAllocation;
	}

	public List<MovieAllocation> getAllmovie() {
List<MovieAllocation> movieAllocations = movieAllocationRepo.findAll();
List<MovieAllocation> movieAllocations2 = new ArrayList<MovieAllocation>();
for (int i = 0; i < movieAllocations.size(); i++) {
	MovieAllocation movieAllocation = movieAllocationRepo.findById(movieAllocations.get(i).getMovieId()).get();
	Path path = Paths.get(movieAllocation.getMoviePath());
	movieAllocation.setMoviePath("../assets/images/"+path.getFileName().toString());
	System.out.println("---Movie Path----"+movieAllocation.getMoviePath());
	movieAllocations2.add(movieAllocation);
}
		return movieAllocations2;
	}

	public MovieAllocation getMoviebyid(int mid) {
		if(movieAllocationRepo.findById(mid).isPresent())
		{
			MovieAllocation movieAllocation = movieAllocationRepo.findById(mid).orElse(null);
			Path path = Paths.get(movieAllocation.getMoviePath());
			movieAllocation.setMoviePath("../assets/images/"+path.getFileName().toString());
			return movieAllocation;
		}
		else 
		{
			return null;
		}
	}

	public MovieAllocation updateMoviedetails(int mid, MovieAllocation ma) {
		MovieAllocation movieAllocation = movieAllocationRepo.findById(mid).orElse(null);
		movieAllocation.setMovieCost(ma.getMovieCost());
		movieAllocation.setMovieDT(ma.getMovieDT());
		movieAllocation.setMovieName(ma.getMovieName());
		movieAllocation.setMoviePath(ma.getMoviePath());
		movieAllocation.setTheaterDetails(ma.getTheaterDetails());
		return movieAllocationRepo.save(movieAllocation);
	}

	public boolean deleteMoviedetails(int mid) {
 if(movieAllocationRepo.findById(mid).isPresent())
{
	 movieAllocationRepo.deleteById(mid);
	 return true;
}
 else {
		return false;
	}}

	
	

}
