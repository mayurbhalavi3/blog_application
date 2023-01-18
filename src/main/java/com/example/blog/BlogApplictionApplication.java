package com.example.blog;

import java.util.List;

import org.modelmapper.ModelMapper;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.example.blog.config.AppConstants;
import com.example.blog.entity.Role;
import com.example.blog.repository.RoleRepo;

@SpringBootApplication
public class BlogApplictionApplication  implements CommandLineRunner{

	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private RoleRepo rolerepo;
	
	public static void main(String[] args) {
		SpringApplication.run(BlogApplictionApplication.class, args);
		
		
		
	}

	
	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
		
		
	}
     
	@Override
	public void run(String... args) throws Exception {
		System.out.println(this.passwordEncoder.encode("abc"));

		try {
			Role role=new Role();
			role.setId(AppConstants.ADMIN_USER);
			role.setName("ROLE_ADMIN");
			
			Role role1=new Role();
			role1.setId(AppConstants.NORMAL_USER);
			role1.setName("ROLE_USER");
			
			List<Role> roles=List.of(role,role1);
		List<Role> result=	this.rolerepo.saveAll(roles);
		
		result.forEach(r->{
			System.out.println(r.getName());
		});
			
		} catch (Exception e) {
			
		}
		
	}
	
}
