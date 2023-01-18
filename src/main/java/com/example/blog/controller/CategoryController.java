package com.example.blog.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.example.blog.payloads.ApiResponse;
import com.example.blog.payloads.CategoryDto;
import com.example.blog.service.CategoryService;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {

	  @Autowired
	  private CategoryService categoryservice;
	
	  
	  @PostMapping("/save")
	  public ResponseEntity<CategoryDto> createCateory(@Valid @RequestBody CategoryDto categorydto){
		CategoryDto catdto=this.categoryservice.createCategory(categorydto);
		return new ResponseEntity<CategoryDto>(catdto,HttpStatus.CREATED);
		  
	  }
	
	  
	  @PutMapping("/update/{catid}")
	  public ResponseEntity<CategoryDto> updateCateory(@Valid @RequestBody CategoryDto categorydto,@PathVariable Integer catid){
		CategoryDto updatecatdto=this.categoryservice.updateCategory(categorydto,catid);
		return new ResponseEntity<CategoryDto>(updatecatdto,HttpStatus.OK);
		  
	  }

	  @DeleteMapping("delete/{categoryid}")
	  public ResponseEntity<ApiResponse> deleteCategory(@PathVariable Integer categoryid) {
		  
		  this.categoryservice.deleteCategory(categoryid);
		return new ResponseEntity<ApiResponse>(new ApiResponse("deleted successfully",true),HttpStatus.OK); 
	  }
	  
	  @GetMapping("/get/{catid}")
	  public ResponseEntity<CategoryDto> getCateory(@PathVariable Integer catid){
		CategoryDto categorydto=this.categoryservice.getCategory(catid);
		return new ResponseEntity<CategoryDto>(categorydto,HttpStatus.OK);
		  
	  }
	  @GetMapping("/getAll")
	  public ResponseEntity<List<CategoryDto>> getAllCategory(){
		List<CategoryDto> catgto=  this.categoryservice.getAllCategory();
		return ResponseEntity.ok(catgto); 
	  }
}
