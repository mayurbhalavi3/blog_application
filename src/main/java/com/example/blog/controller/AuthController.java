package com.example.blog.controller;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.blog.exception.ApiException;
import com.example.blog.payloads.JwtAuthRequest;
import com.example.blog.payloads.JwtAuthResponse;
import com.example.blog.payloads.UserDto;
import com.example.blog.security.JwtTokenHelper;
import com.example.blog.service.UserService;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

	
	@Autowired
	private JwtTokenHelper jwttokenhelper;
	
	@Autowired
	private UserDetailsService userdetailservice;
	
	
	@Autowired
	private AuthenticationManager authenticationmanager;
	
	
	@Autowired
	private UserService userservice;
	
	@PostMapping("/login")
	public ResponseEntity<com.example.blog.payloads.JwtAuthResponse> createToken(
		@RequestBody JwtAuthRequest request
			) throws Exception{
		
		this.authenticate(request.getUsername(),request.getPassword());
		UserDetails userdetails=this.userdetailservice.loadUserByUsername(request.getUsername());
		String token=this.jwttokenhelper.generateToken(userdetails);
		
		JwtAuthResponse response=new JwtAuthResponse();
		response.setToken(token);
		return new ResponseEntity<JwtAuthResponse>(response,HttpStatus.OK);
		
				
		
		
		
		
		
	}



	private void authenticate(String username, String password) throws Exception {
		UsernamePasswordAuthenticationToken authenticationtoken=new UsernamePasswordAuthenticationToken(username, password);
		
		try {
		    this.authenticationmanager.authenticate(authenticationtoken);	
		
		} catch (BadCredentialsException e) {
	   System.out.println("Invalid Details");
	   
	   throw new ApiException("Invalid username or password!!");
		}
	}
	
	
	@PostMapping("/register")
	   public ResponseEntity<UserDto> registerUser(@RequestBody UserDto userdto){
		   
		UserDto registeruser=   this.userservice.registerNewUser(userdto);
		   
		   return new ResponseEntity<UserDto>(registeruser,HttpStatus.CREATED);
	   }
	
}
