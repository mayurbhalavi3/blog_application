package com.example.blog.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.blog.entity.Comment;
import com.example.blog.entity.Post;
import com.example.blog.exception.ResourceNotFoundException;
import com.example.blog.payloads.CommentDto;
import com.example.blog.repository.CommentRepo;
import com.example.blog.repository.PostRepo;



@Service
public class CommentServiceImpl implements CommentService{

	@Autowired
	private CommentRepo commentrepo;
	
	@Autowired
	private PostRepo postrepo;
	
	@Autowired
	private ModelMapper modelmapper;
	
	
	@Override
	public CommentDto createComment(CommentDto commentdto, Integer postid) {
		Post post=this.postrepo.findById(postid).orElseThrow(()-> new ResourceNotFoundException("Post", "postid", postid));
		Comment comment=this.modelmapper.map(commentdto, Comment.class);
		comment.setPost(post);
		Comment newcomment=this.commentrepo.save(comment);
		return this.modelmapper.map(newcomment, CommentDto.class);
	}

	@Override
	public void deleteComment(Integer commentid) {
	Comment comment=this.commentrepo.findById(commentid).orElseThrow(()-> new ResourceNotFoundException("Comment", "commentid", commentid));
		this.commentrepo.delete(comment);
		
	}

}
