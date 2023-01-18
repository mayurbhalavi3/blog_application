package com.example.blog.controller;

import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.blog.payloads.ApiResponse;
import com.example.blog.payloads.UserDto;
import com.example.blog.service.UserService;

@RestController
@RequestMapping("/api/users")
public class UserController {

	@Autowired
	private UserService userservice;
	
	@PostMapping("/save")
	public ResponseEntity<UserDto> createUser(@Valid @RequestBody UserDto userDto){
		    
		   UserDto userdto1=this.userservice.createUser(userDto);
		
		   return new ResponseEntity<>(userdto1,HttpStatus.CREATED);
	}
	
	@PutMapping("/update/{userid}")
	public ResponseEntity<UserDto> updateUser(@Valid @RequestBody UserDto userdto,@PathVariable("userid") Integer id){
		
		UserDto updateUser=this.userservice.updateUser(userdto, id);
		return ResponseEntity.ok(updateUser);
		
	}
	@PreAuthorize("hasRole('ADMIN')") 
	@DeleteMapping("/delete/{userid}")
	public ResponseEntity<?> deleteUser(@PathVariable("userid") Integer id){
		this.userservice.deleteUser(id);
		return new ResponseEntity(new ApiResponse("User deleted successfully",true),HttpStatus.OK);
			
	}
	
	@GetMapping("/get/{userid}")
	public ResponseEntity<UserDto> getSingleUser(@PathVariable Integer userid){
	
		return ResponseEntity.ok(this.userservice.getUserById(userid));
		
	}
	
	@GetMapping("/getAll")
	public ResponseEntity<List<UserDto>> getAllUser(){
		return ResponseEntity.ok(this.userservice.getAllUser());
		
		
	}
	
}
