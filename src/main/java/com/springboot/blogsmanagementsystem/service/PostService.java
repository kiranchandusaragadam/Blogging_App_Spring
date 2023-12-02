package com.springboot.blogsmanagementsystem.service;

import com.springboot.blogsmanagementsystem.dto.requestDto.PostRequestDto;
import com.springboot.blogsmanagementsystem.dto.responseDto.PostResponseDto;
import com.springboot.blogsmanagementsystem.exception.ResourceNotFoundException;

import java.util.List;

public interface PostService {
    // create and add post
    PostResponseDto addPost(PostRequestDto postRequestDto, int userId, int catId) throws ResourceNotFoundException;

    // get post by id
    PostResponseDto getPostById(int postId) throws ResourceNotFoundException;

    // get posts by category
    List<PostResponseDto> getPostsByCategory(int catId) throws ResourceNotFoundException;

    // get posts by user
    List<PostResponseDto> getPostsByUser(int userId) throws ResourceNotFoundException;

    // get all posts
    List<PostResponseDto> getAllPosts();

    // update post details
    PostResponseDto updatePost(PostRequestDto postRequestDto, int postId) throws ResourceNotFoundException;

    // delete post
    void deletePost(int postId) throws ResourceNotFoundException;
}
