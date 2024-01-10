package com.newlearn.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.newlearn.Entity.UserEntity;

@Repository
public interface UserRepository  extends JpaRepository<UserEntity, String>{

}
