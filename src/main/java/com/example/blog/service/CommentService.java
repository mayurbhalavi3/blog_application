package com.example.blog.service;

import com.example.blog.payloads.CommentDto;

public interface CommentService {

	CommentDto createComment(CommentDto commentdto,Integer postid);
	
	void deleteComment(Integer commentid);
	
	
	
}
