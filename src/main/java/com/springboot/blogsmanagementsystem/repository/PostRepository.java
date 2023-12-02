package com.springboot.blogsmanagementsystem.repository;

import com.springboot.blogsmanagementsystem.entity.Category;
import com.springboot.blogsmanagementsystem.entity.Post;
import com.springboot.blogsmanagementsystem.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Integer> {
    List<Post> findByCategory(Category category);

    List<Post> findByUser(User user);
}
