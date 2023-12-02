package com.springboot.blogsmanagementsystem.repository;

import com.springboot.blogsmanagementsystem.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Integer> {
}
