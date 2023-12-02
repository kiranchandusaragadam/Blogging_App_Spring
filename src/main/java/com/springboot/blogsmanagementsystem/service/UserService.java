package com.springboot.blogsmanagementsystem.service;

import com.springboot.blogsmanagementsystem.dto.requestDto.CommentRequestDto;
import com.springboot.blogsmanagementsystem.dto.requestDto.UserRequestDto;
import com.springboot.blogsmanagementsystem.dto.responseDto.CommentResponseDto;
import com.springboot.blogsmanagementsystem.dto.responseDto.GeneralResponseDto;
import com.springboot.blogsmanagementsystem.dto.responseDto.UserResponseDto;
import com.springboot.blogsmanagementsystem.exception.ResourceNotFoundException;

import java.util.List;

public interface UserService {

    UserResponseDto addUser(UserRequestDto userRequestDto);

    UserResponseDto getUserById(int userId) throws ResourceNotFoundException;

    List<UserResponseDto> getAllUsers();

    UserResponseDto updateUserById(UserRequestDto userRequestDto, int userId) throws ResourceNotFoundException;

    void deleteUserById(int userId) throws ResourceNotFoundException;

    // comment on post by passing post id, user id
    CommentResponseDto commentOnPost(CommentRequestDto commentRequestDto, int userId, int postId) throws ResourceNotFoundException;

    // delete comment by that user in corresponding post
    GeneralResponseDto deleteComment(int userId, int postId, int commentId) throws ResourceNotFoundException;
}
