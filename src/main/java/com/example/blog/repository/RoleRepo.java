package com.example.blog.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.blog.entity.Role;

public interface RoleRepo extends JpaRepository<Role, Integer> {

}
