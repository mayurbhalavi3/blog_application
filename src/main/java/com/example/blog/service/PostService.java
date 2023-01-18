package com.example.blog.service;

import java.util.List;

import com.example.blog.entity.Post;
import com.example.blog.payloads.PostDto;
import com.example.blog.payloads.PostResponse;

public interface PostService {

	
	public PostDto createPost(PostDto postdto,Integer userid,Integer categoryid );
	
	
	PostDto updatePost(PostDto postdto,Integer postId);
	
	
	void deletePost(Integer postid);
	
	
	PostResponse getAllPost(Integer pagenumber,Integer pagesize,String sortby,String sortdir);
	
	PostDto getPostById(Integer postid);
	
	
	List<PostDto> getPostByUser(Integer userid);
	
	List<PostDto> getPostByCategory(Integer categoryid);
	
	
	List<PostDto> searchPosts(String keyword);
	
	
}
