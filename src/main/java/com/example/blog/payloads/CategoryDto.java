package com.example.blog.payloads;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Setter
@Getter
public class CategoryDto {

	private int categoryid;
	@NotEmpty
	@Size(min=4,message ="Username must be min of 4 characters !!")
	private String categorytitle;
	@NotEmpty
	@Size(min=4,max=200,message ="Username must be min of 4 characters !!")
	private String categorydescription;
	
	
}
