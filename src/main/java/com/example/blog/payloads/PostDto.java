package com.example.blog.payloads;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.validation.constraints.NotEmpty;

import com.example.blog.entity.Category;
import com.example.blog.entity.Comment;
import com.example.blog.entity.User;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Setter
@Getter
public class PostDto {

	
	@NotEmpty
	public String title;
	
	
	
	@NotEmpty
	public String content;
	
	private String imagename;
	
	private Date date;
	
	private CategoryDto category;
	  
	private UserDto user; 
	
	private Set<Comment> comments=new HashSet<>();
	
}
