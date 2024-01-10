package com.newlearn.Controller;


import java.util.List;
import java.util.Random;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.newlearn.Entity.UserEntity;
import com.newlearn.service.UserService;

@RestController
@RequestMapping("/users")
public class UserController {
	
	@Autowired
	private UserService service;
	
	@PostMapping
	public ResponseEntity<UserEntity> createservice (@RequestBody UserEntity user)
	{
        UserEntity entity = service.saveUser(user);
       return ResponseEntity.status(HttpStatus.CREATED).body(entity);
   }
	
	@GetMapping
	public ResponseEntity<List<UserEntity>> getlist()
	{
      List<UserEntity> listuser = service.userlist();
	return ResponseEntity.ok(listuser);
	}
	
	@GetMapping("/{userId}")
	public ResponseEntity<UserEntity> getUserId(@PathVariable("userId") String  userId)
	{
	UserEntity user1 = service.getUser(userId);
	return ResponseEntity.ok(user1);
	}


}
