package com.example.blog.payloads;

import java.util.HashSet;
import java.util.Set;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.example.blog.entity.Role;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Setter
@Getter
@NoArgsConstructor
public class  UserDto {

	private int id;
	@NotEmpty
	@Size(min=4,message ="Username must be min of 4 characters !!")
	private String name;
	
	
	@Email(message="email address is not valid!!")
	private String email;
	
	@NotEmpty
	@Size(min=3,max=4, message="password min of 3 and max of 10 characters!!")
	private String password;
	@NotEmpty
	private String about;
	
	private Set<RoleDto> roles= new HashSet<>();
	
	
}
