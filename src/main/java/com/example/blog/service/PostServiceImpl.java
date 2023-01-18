package com.example.blog.service;

import java.util.Date;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.example.blog.entity.Category;
import com.example.blog.entity.Post;
import com.example.blog.entity.User;
import com.example.blog.exception.ResourceNotFoundException;
import com.example.blog.payloads.PostDto;
import com.example.blog.payloads.PostResponse;
import com.example.blog.repository.CategoryRepo;
import com.example.blog.repository.PostRepo;
import com.example.blog.repository.UserRepo;

@Service
public class PostServiceImpl implements PostService {

	@Autowired
	private PostRepo postrepo;
	
	@Autowired
	private ModelMapper modelmapper;
	
	@Autowired
	private UserRepo userrepo;
	
	@Autowired
	private CategoryRepo categoryrepo;
	
	@Override
	public PostDto createPost(PostDto postdto,Integer userid,Integer categoryid) {
		
		User user=this.userrepo.findById(userid).orElseThrow(()-> new ResourceNotFoundException("User", "userid", userid));
		Category category=this.categoryrepo.findById(categoryid).orElseThrow(()-> new ResourceNotFoundException("Category", "categoryid", userid));  
		
		Post  post=this.modelmapper.map(postdto, Post.class);
		post.setImagename("default.png");
		post.setDate(new Date());  
		post.setUser(user);
		post.setCategory(category);
		Post newpost=this.postrepo.save(post);
		return this.modelmapper.map(newpost, PostDto.class);   
	}

	@Override
	public PostDto updatePost(PostDto postdto, Integer postId) {
		Post post=this.postrepo.findById(postId).orElseThrow(()-> new ResourceNotFoundException("Post", "postId", postId));
		post.setTitle(postdto.getTitle());
		post.setContent(postdto.getContent());
		post.setImagename(postdto.getImagename());
		post.setDate(postdto.getDate());
		Post post1=this.postrepo.save(post);
		return this.modelmapper.map(post1, PostDto.class);
	}

	@Override
	public void deletePost(Integer postid) {
		
	
	 	
		Post post=this.postrepo.findById(postid).orElseThrow(()-> new ResourceNotFoundException("Post", "postid", postid));
		this.postrepo.deleteById(postid);
	}

	@Override
	public PostResponse getAllPost(Integer pagenumber,Integer pagesize,String sortby,String sortdir) {
	Sort sort=	(sortdir.equalsIgnoreCase("asc")?Sort.by(sortby).ascending():Sort.by(sortby).descending());
		PageRequest p=PageRequest.of(pagenumber, pagesize, sort);
		Page<Post> pagepost=this.postrepo.findAll(p);
		List<Post> allpost=pagepost.getContent();
		List<PostDto> postdto=allpost.stream().map((post)->this.modelmapper.map(post, PostDto.class)).collect(Collectors.toList());
		 PostResponse pr=new PostResponse();
		 pr.setContent(postdto);
		 pr.setPagenumber(pagepost.getNumber());
		 pr.setPagesize(pagepost.getSize());
		 pr.setTotalelements(pagepost.getTotalElements());
		 pr.setTotalpages(pagepost.getTotalPages());
		 pr.setLastpage(pagepost.isLast());
		 
		 
		
		return pr;
	
	}

	@Override
	public PostDto getPostById(Integer postid) {
	Post posts=	this.postrepo.findById(postid).orElseThrow(()-> new ResourceNotFoundException("Post", "postid", postid));
		return this.modelmapper.map(posts, PostDto.class);
	}
   
	@Override
	public List<PostDto> getPostByUser(Integer userid) {
	 User user=  this.userrepo.findById(userid).orElseThrow(()-> new ResourceNotFoundException("User", "userid", userid));
	List<Post> posts=this.postrepo.findByUser(user); 
	List<PostDto> postdto=posts.stream().map((post)->this.modelmapper.map(post, PostDto.class)).collect(Collectors.toList());
	return postdto;
	 
	 
	}

	@Override
	public List<PostDto> searchPosts(String keyword) {
		List<Post> post=this.postrepo.searchByTitle("%"+keyword+"%");
	List<PostDto> postdto=	post.stream().map((posts)->this.modelmapper.map(posts, PostDto.class)).collect(Collectors.toList());
	return postdto;
	}

	@Override
	public List<PostDto> getPostByCategory(Integer categoryid) {
	Category cat=this.categoryrepo.findById(categoryid).orElseThrow(()-> new ResourceNotFoundException("Category", "categoryid", categoryid));
	List<Post> posts=this.postrepo.findByCategory(cat);
	List<PostDto> postdto=posts.stream().map((post)->this.modelmapper.map(post, PostDto.class)).collect(Collectors.toList());
	return postdto;
	}

	

}
