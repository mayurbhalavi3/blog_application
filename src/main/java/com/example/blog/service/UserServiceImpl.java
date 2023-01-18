package com.example.blog.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.blog.config.AppConstants;
import com.example.blog.entity.Role;
import com.example.blog.entity.User;
import com.example.blog.exception.ResourceNotFoundException;
import com.example.blog.payloads.UserDto;
import com.example.blog.repository.RoleRepo;
import com.example.blog.repository.UserRepo;

@Service
public class UserServiceImpl implements UserService{

	@Autowired
	private UserRepo userrepo; 
	
	@Autowired
	private ModelMapper modelmapper;
	
	@Autowired
	private PasswordEncoder passwordencoder;
	
	@Autowired
	private RoleRepo rolerepo;
	   
	@Override
	public UserDto createUser(UserDto userDto) {
		User user=this.dtoToUser(userDto);
		User saveuser=this.userrepo.save(user);
		return this.userToDto(saveuser);
	}

	@Override
	public UserDto updateUser(UserDto userDto, Integer userId) {
		User user=this.userrepo.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User"," Id ",userId));
		user.setName(userDto.getName());
		user.setEmail(userDto.getEmail());
		user.setPassword(userDto.getPassword());
		user.setAbout(userDto.getAbout());
		
		User updateuser=this.userrepo.save(user);
		UserDto userdto1=this.userToDto(updateuser);
		return userdto1;
	}

	@Override
	public UserDto getUserById(Integer userId) {
		
		User user=this.userrepo.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User", "Id", userId));
		
		return this.userToDto(user);
	}

	@Override
	public List<UserDto> getAllUser() {
		List<User> users=this.userrepo.findAll();
		List<UserDto> userdto=users.stream().map(user -> this.userToDto(user)).collect(Collectors.toList() );
		return userdto;
	}

	@Override
	public void deleteUser(Integer userId) {
	User user= this.userrepo.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User","Id",userId));
		this.userrepo.delete(user);
	}
	
	private User dtoToUser(UserDto userDto) {
		User user=this.modelmapper.map(userDto, User.class);
		/*
		user.setId(userDto.getId());
		user.setName(userDto.getName());
		user.setEmail(userDto.getEmail());
		user.setAbout(userDto.getAbout());
		user.setPassword(userDto.getPassword());
		*/
		return user;
		
	}

	private UserDto userToDto(User user) {
		UserDto userdto=this.modelmapper.map(user, UserDto.class);
		/*
		userdto.setId(user.getId());
		userdto.setName(user.getName());
		userdto.setEmail(user.getEmail());
		userdto.setPassword(user.getPassword());
		userdto.setAbout(user.getAbout());
		*/
		return userdto;
		
		
	}

	@Override
	public UserDto registerNewUser(UserDto userdto) {
	User user=	this.modelmapper.map(userdto, User.class);
	user.setPassword(this.passwordencoder.encode(user.getPassword()));
	
   Role role=this.rolerepo.findById(AppConstants.NORMAL_USER).get();
   
        user.getRoles().add(role);
        User newuser= this.userrepo.save(user);
       return this.modelmapper.map(newuser, UserDto.class);
        
		
	}
	
}
