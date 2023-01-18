package com.example.blog.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;



@Entity
@Data
public class Role {

	
	@Id 
	private int id;
	
	@Column(name="name",nullable = false, length=100)
	private String name;
	


}
