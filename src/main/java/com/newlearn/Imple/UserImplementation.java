package com.newlearn.Imple;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.newlearn.Entity.Hotel;
import com.newlearn.Entity.Rating;
import com.newlearn.Entity.UserEntity;
import com.newlearn.Exception.ResourceNotFoundException;

import com.newlearn.Repository.UserRepository;
import com.newlearn.service.UserService;

@Service
public class UserImplementation implements UserService{

	@Autowired
	private UserRepository repository;
	
	@Autowired
	private RestTemplate restTemplate;
	
	
	private org.slf4j.Logger logger = LoggerFactory.getLogger(UserImplementation.class);
	
	
	@Override
	public UserEntity saveUser(UserEntity user) {
		String random = UUID.randomUUID().toString();
		  user.setUserId(random);
		return repository.save(user);
	}

	@Override
	public List<UserEntity> userlist() {
		return repository.findAll();
	}

	
	@Override
	public UserEntity getUser(String userId) {
		// TODO Auto-generated method stub
	UserEntity user =  (UserEntity) repository.findById(userId).orElseThrow(()-> new ResourceNotFoundException(""+userId));
		
		//fetch rating of the above user from Rating Service (dynamic id)
		
		//REST-TEMPLATE use to communicate two ore more service
		//http://localhost:9092/rating/user/1a2c966b-4c1f-4728-94e5-69033f122e19
	//http://localhost:9092/ratings/user/0a8d2b44-0504-4b34-ab84-417dd875f270
		
		Rating[] ratingofUser = (Rating[]) restTemplate.getForObject("http://RATING-SERVICE/ratings/user/"+user.getUserId(),Rating[].class);
		
		   List<Rating>ratings =Arrays.stream(ratingofUser).toList();

		
	  List<Rating> ratingList = ratings.stream().map(rating -> {
		  //api call to hotel service to get the hotel //set the hotel to rating
		// it used through RestTemplate client to communicate with service
		 ResponseEntity<Hotel> hotelresponse = restTemplate.getForEntity("http://HOTEL-SERVICE/hotels/"+rating.getHotelId(), Hotel.class);
		Hotel hotel  =hotelresponse.getBody();
		  
		  
		  //using Feign Client communicate with service like hotel to rating 
		  
		  //Hotel hotel =hotelService.getHotel(rating.getHotelId());
		  
	  //set the hotel to rating
		rating.setHotel(hotel);
		//return the rating
		return rating;  
		 
	  }).collect(Collectors.toList());	
		
	  user.setRating(ratingList);  
       return user;
		  
	                      }
	
	
	
	
	

}
