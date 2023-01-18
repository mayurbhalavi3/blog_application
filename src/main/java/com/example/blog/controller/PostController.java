package com.example.blog.controller;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.hibernate.engine.jdbc.StreamUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.blog.config.AppConstants;
import com.example.blog.payloads.ApiResponse;
import com.example.blog.payloads.PostDto;
import com.example.blog.payloads.PostResponse;
import com.example.blog.service.FileService;
import com.example.blog.service.PostService;

@RestController
@RequestMapping("/api/")
public class PostController { 

	@Autowired
	private PostService postservice;
	
	@Autowired
	private FileService fileservice;
	
	
	@Value("${project.image}")
	private String path;
	
	
	@PostMapping("/user/{userid}/category/{categoryid}/posts")
	public ResponseEntity<PostDto> createPost(@Valid @RequestBody PostDto postdto,@PathVariable Integer userid,@PathVariable Integer categoryid ){
	
		PostDto createpost= this.postservice.createPost(postdto, userid, categoryid);
		
		
		return new ResponseEntity<PostDto>(createpost,HttpStatus.CREATED);
		
		
	}
	
	@PutMapping("/post/update/{postid}")
	public ResponseEntity<PostDto> updatePost(@Valid @RequestBody PostDto postdto,@PathVariable Integer postid){
		PostDto postdto1=this.postservice.updatePost(postdto, postid);
		return new ResponseEntity<PostDto>(postdto1,HttpStatus.OK);
		
	}
	
	@GetMapping("/post/get/{postid}")
	public ResponseEntity<PostDto> getPostById(@PathVariable Integer postid){
	 PostDto postdto=this.postservice.getPostById(postid);
		return new ResponseEntity<PostDto>(postdto,HttpStatus.OK);
		
		
	}
	
	@GetMapping("/post/getAll")
	public ResponseEntity<PostResponse> getAllPost(
			@RequestParam(value= "pagenumber", defaultValue = AppConstants.PAGE_NUMBER, required = false)Integer pagenumber,
			@RequestParam(value = "pagesize",defaultValue = AppConstants.PAGE_SIZE,required = false) Integer pagesize,
			@RequestParam(value= "sortby", defaultValue = AppConstants.SORT_BY, required = false)String sortby,
			@RequestParam(value= "sortdir", defaultValue = AppConstants.SORT_DIR, required = false)String sortdir){
		PostResponse postresponse=this.postservice.getAllPost(pagenumber,pagesize,sortby,sortdir);
		return new ResponseEntity<PostResponse>(postresponse,HttpStatus.OK);
	}
	 
	@DeleteMapping("/post/delete/{postid}")
	public ResponseEntity<ApiResponse> deletePost(@PathVariable Integer postid){
		this.postservice.deletePost(postid);
		return new ResponseEntity<ApiResponse>( new ApiResponse("post deleted succesfully",true), HttpStatus.OK);
		
	}
	
	
	@GetMapping("/user/{userid}/posts")
	public ResponseEntity<List<PostDto>> getPostByUser(@PathVariable Integer userid)
	{
		List<PostDto> postdto=this.postservice.getPostByUser(userid);
		
	return new ResponseEntity<List<PostDto>>(postdto,HttpStatus.OK);	
	}
	
	
	@GetMapping("/category/{categoryid}/posts")
	public ResponseEntity<List<PostDto>> getPostByCategory(@PathVariable Integer categoryid)
	{
		List<PostDto> postdto=this.postservice.getPostByCategory(categoryid);
		
	return new ResponseEntity<List<PostDto>>(postdto,HttpStatus.OK);	
	}
	
	@GetMapping("/post/search/{keyword}")
	public ResponseEntity<List<PostDto>> searchPosts(@PathVariable("keyword") String keyword ){
		
		List<PostDto> result=this.postservice.searchPosts(keyword);
		return new ResponseEntity<List<PostDto>>(result,HttpStatus.OK);
	}
	
	
	  @PostMapping("/post/image/upload/{postid}")
	public ResponseEntity<PostDto> uploadPostImage(
			@RequestParam("image") MultipartFile image,
			@PathVariable Integer postid)throws IOException{
		  
		 
		
		String filename=this.fileservice.uploadImage( path, image);
		PostDto postdto=this .postservice.getPostById(postid);
		postdto.setImagename(filename);
		PostDto postdto1=this.postservice.updatePost(postdto, postid);
		return new ResponseEntity<PostDto>(postdto1,HttpStatus.OK);
		
	}
	
	    @GetMapping(value="/post/image/{imagename}",produces = MediaType.IMAGE_JPEG_VALUE)
	    public void downloadImage(
	    		@PathVariable("imagename") String imagename,
	    		HttpServletResponse response) throws IOException{
	    	
	    	InputStream resource=this.fileservice.getResource(path, imagename);
	    	response.setContentType(MediaType.IMAGE_JPEG_VALUE);
	    	StreamUtils.copy(resource, response.getOutputStream());
	    	
	    }
	
	
	
	
	
	
	
	
	
	
	
}
