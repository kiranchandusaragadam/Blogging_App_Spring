package com.springboot.blogsmanagementsystem.controller;

import com.springboot.blogsmanagementsystem.dto.requestDto.CommentRequestDto;
import com.springboot.blogsmanagementsystem.dto.requestDto.UserRequestDto;
import com.springboot.blogsmanagementsystem.dto.responseDto.CommentResponseDto;
import com.springboot.blogsmanagementsystem.dto.responseDto.GeneralResponseDto;
import com.springboot.blogsmanagementsystem.dto.responseDto.UserResponseDto;
import com.springboot.blogsmanagementsystem.exception.ResourceNotFoundException;
import com.springboot.blogsmanagementsystem.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    UserService userService;

    // add user to database
    @PostMapping("/add")
    public ResponseEntity<UserResponseDto> addUser(@Valid @RequestBody UserRequestDto userRequestDto){
        UserResponseDto savedUserDto = userService.addUser(userRequestDto);
        return new ResponseEntity<>(savedUserDto, HttpStatus.CREATED);
    }

    // get user by id
    @GetMapping("/get/{id}")
    public ResponseEntity<UserResponseDto> getUserById(@PathVariable("id") int userId) throws ResourceNotFoundException {
        UserResponseDto userDto = userService.getUserById(userId);
        return new ResponseEntity<>(userDto, HttpStatus.OK);
    }

    // get all users
    @GetMapping("/get-all")
    public ResponseEntity<List<UserResponseDto>> getAllUsers(){
        List<UserResponseDto> userDtos = userService.getAllUsers();
        return new ResponseEntity<>(userDtos, HttpStatus.OK);
    }

    // update user with given id and dto
    @PutMapping("/update/{id}")
    public ResponseEntity<UserResponseDto> updateUserById(@Valid @RequestBody UserRequestDto userRequestDto, @PathVariable("id") int userId) throws ResourceNotFoundException {
        UserResponseDto userDto = userService.updateUserById(userRequestDto, userId);
        return new ResponseEntity<>(userDto, HttpStatus.OK);
    }

    // delete user by id
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<GeneralResponseDto> deleteUserById(@PathVariable("id") int userId) throws ResourceNotFoundException {
        userService.deleteUserById(userId);
        return new ResponseEntity<>(new GeneralResponseDto("User deleted successfully", true), HttpStatus.OK);
    }

    // comment on post
    @PostMapping("/{userId}/comment/post/{postId}")
    public ResponseEntity<CommentResponseDto> commentOnPost(@Valid @RequestBody CommentRequestDto commentRequestDto,
                                                            @PathVariable int userId,
                                                            @PathVariable int postId) throws ResourceNotFoundException {
        CommentResponseDto comment = userService.commentOnPost(commentRequestDto, userId, postId);
        return new ResponseEntity<>(comment, HttpStatus.CREATED);
    }

    // delete comment
    @DeleteMapping("/{userId}/post/{postId}/comment/{commentId}/delete")
    public ResponseEntity<GeneralResponseDto> deleteComment(@PathVariable int userId,
                                                            @PathVariable int postId,
                                                            @PathVariable int commentId) throws ResourceNotFoundException {
        return new ResponseEntity<>(userService.deleteComment(userId, postId, commentId), HttpStatus.OK);
    }
}
