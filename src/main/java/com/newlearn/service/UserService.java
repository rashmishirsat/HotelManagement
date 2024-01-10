package com.newlearn.service;

import java.util.List;


import org.springframework.stereotype.Service;

import com.newlearn.Entity.UserEntity;


@Service
public interface UserService {

	
	
	UserEntity saveUser(UserEntity user);

	
	List<UserEntity> userlist();
	
	
	
	UserEntity getUser(String userId);
	
}
