package com.example.blog.security;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.blog.entity.User;
import com.example.blog.exception.ResourceNotFoundException;
import com.example.blog.repository.UserRepo;

@Service
public class CustomUserServiceDetail implements UserDetailsService {

	@Autowired
	private UserRepo userrepo;
	
	
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
	User user=	this.userrepo.findByEmail(username).orElseThrow(()-> new ResourceNotFoundException("User", "email:"+username, 0));
		return user;
	}

}
