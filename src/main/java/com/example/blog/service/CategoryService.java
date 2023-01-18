package com.example.blog.service;

import java.util.List;

import com.example.blog.payloads.CategoryDto;

public interface CategoryService {

	
	public CategoryDto createCategory(CategoryDto categorydto);
	
	
	public CategoryDto updateCategory(CategoryDto categorydto,Integer categoryid);
	
      	
	public void deleteCategory(Integer categorydto);
	
	public CategoryDto getCategory(Integer categoryid);
	
	
	List<CategoryDto> getAllCategory();
}
