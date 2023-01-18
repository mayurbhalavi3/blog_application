package com.example.blog.payloads;

import java.util.List;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;



@NoArgsConstructor
@Setter
@Getter
public class PostResponse {

	
	private List<PostDto> content;
	
	private int pagenumber;
	private int pagesize;
	private long totalelements;
	private int totalpages;
	private boolean lastpage; 
	
}
