package com.example.blog.service;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.blog.entity.Category;
import com.example.blog.exception.ResourceNotFoundException;
import com.example.blog.payloads.CategoryDto;
import com.example.blog.repository.CategoryRepo;


@Service
public class CategoryServiceImpl implements CategoryService  {

	@Autowired
	private CategoryRepo categoryrepo;
	@Autowired 
	private ModelMapper modelmapper;
	 
	@Override
	public CategoryDto createCategory(CategoryDto categorydto) {
		Category cat=this.modelmapper.map(categorydto, Category.class);
		Category addedcat=this.categoryrepo.save(cat);
		return this.modelmapper.map(addedcat, CategoryDto.class);
	}

	@Override
	public CategoryDto updateCategory(CategoryDto categorydto, Integer categoryid) {
		Category cat=this.categoryrepo.findById(categoryid).orElseThrow(()-> new ResourceNotFoundException("Category", "categoryid", categoryid));
		cat.setCategorytitle(categorydto.getCategorytitle());
		cat.setCategorydescription(categorydto.getCategorydescription());
		Category updatecat=this.categoryrepo.save(cat);
		
		
		return this.modelmapper.map(updatecat, CategoryDto.class);
	}

	@Override
	public void deleteCategory(Integer categorydto) {
	Category cat=	this.categoryrepo.findById(categorydto).orElseThrow(()-> new ResourceNotFoundException("Category", "categoryid", categorydto));
		this.categoryrepo.delete(cat);
	}

	@Override
	public CategoryDto getCategory(Integer categoryid) {
	 Category cat=this.categoryrepo.findById(categoryid).orElseThrow(()-> new ResourceNotFoundException("Category", "categoryid", categoryid));
		return this.modelmapper.map(cat, CategoryDto.class);
		
	}

	@Override
	public List<CategoryDto> getAllCategory() {
		List<Category> li=this.categoryrepo.findAll();
	List<CategoryDto> lidto= li.stream().map((cat)->this.modelmapper.map(cat,CategoryDto.class)).collect((Collectors.toList()));
		return lidto; 
	}

}
