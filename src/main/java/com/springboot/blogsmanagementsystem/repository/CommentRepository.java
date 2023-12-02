package com.springboot.blogsmanagementsystem.repository;

import com.springboot.blogsmanagementsystem.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Integer> {
}
