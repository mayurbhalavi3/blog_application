package com.example.blog.service;


import java.util.List;

import com.example.blog.payloads.UserDto;

public interface UserService {

	public UserDto createUser(UserDto userDto); 
	
	UserDto updateUser(UserDto userDto,Integer userId);
	
	UserDto getUserById(Integer userId);
	
	List<UserDto> getAllUser();
	
	void deleteUser(Integer userId);
	
	UserDto registerNewUser (UserDto userdto);
}
