package com.example.blog.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.blog.entity.User;




@Repository
public interface UserRepo extends JpaRepository<User, Integer> {

	
	Optional<User> findByEmail(String email);
	
}
