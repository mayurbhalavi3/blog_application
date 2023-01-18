package com.example.blog.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.blog.payloads.ApiResponse;
import com.example.blog.payloads.CommentDto;
import com.example.blog.service.CommentService;

@RestController
@RequestMapping("/api/")
public class CommentController {
       
	@Autowired
	private CommentService commentservice;
	
	@PostMapping("/post/{postid}/comments")
	public ResponseEntity<CommentDto> createComment(@RequestBody CommentDto comment,@PathVariable Integer postid){
		
		CommentDto createcomment= this.commentservice.createComment(comment, postid);
		return new ResponseEntity<CommentDto>(createcomment,HttpStatus.CREATED);
		
	}
	@DeleteMapping("/delete/{commentid}")
	public ResponseEntity<ApiResponse> deleteComment (@PathVariable Integer commentid){
		
		this.commentservice.deleteComment(commentid);
		
		
		return new ResponseEntity<ApiResponse>(new ApiResponse("comment deleted successfully",true),HttpStatus.OK);
		
		
	}
	
	
}
