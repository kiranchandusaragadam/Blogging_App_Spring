package com.springboot.blogsmanagementsystem.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.springboot.blogsmanagementsystem.dto.requestDto.PostRequestDto;
import com.springboot.blogsmanagementsystem.dto.responseDto.GeneralResponseDto;
import com.springboot.blogsmanagementsystem.dto.responseDto.PostResponseDto;
import com.springboot.blogsmanagementsystem.exception.ResourceNotFoundException;
import com.springboot.blogsmanagementsystem.service.FileService;
import com.springboot.blogsmanagementsystem.service.PostService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/posts")
public class PostController {
    @Autowired
    PostService postService;

    @Autowired
    FileService fileService;

    @Autowired
    ObjectMapper objectMapper;

    @Value("${project.image}")
    private String path;

    // create post
    @PostMapping("/add/user/{userId}/category/{catId}")
    public ResponseEntity<PostResponseDto> addPost(@Valid @RequestParam("postData") String postData,
                                                   @RequestParam("image") MultipartFile multipartFile,
                                                   @PathVariable int userId,
                                                   @PathVariable int catId) throws ResourceNotFoundException, IOException {

        // convert the post data into post request dto using object mapper
        PostRequestDto postRequestDto = objectMapper.readValue(postData, PostRequestDto.class);

        // upload image and get that file name
        String fileName = fileService.uploadImage(path, multipartFile);

        // set that uploaded file name in post request dto
        postRequestDto.setImageName(fileName);
        PostResponseDto newPost = postService.addPost(postRequestDto, userId, catId);

        return new ResponseEntity<>(newPost, HttpStatus.CREATED);
    }

    // get post by id
    @GetMapping("/get/{postId}")
    public ResponseEntity<PostResponseDto> getPostById(@PathVariable int postId) throws ResourceNotFoundException {
        PostResponseDto savedPost = postService.getPostById(postId);
        return new ResponseEntity<>(savedPost, HttpStatus.OK);
    }

    // get posts by category
    @GetMapping("/get-by-category/{catId}")
    public ResponseEntity<List<PostResponseDto>> getPostsByCategory(@PathVariable int catId) throws ResourceNotFoundException {
        List<PostResponseDto> postResponseDtoList = postService.getPostsByCategory(catId);
        return new ResponseEntity<>(postResponseDtoList, HttpStatus.OK);
    }

    // get posts by user
    @GetMapping("/get-by-user/{userId}")
    public ResponseEntity<List<PostResponseDto>> getPostsByUser(@PathVariable int userId) throws ResourceNotFoundException{
        List<PostResponseDto> postResponseDtoList = postService.getPostsByUser(userId);
        return new ResponseEntity<>(postResponseDtoList, HttpStatus.OK);
    }

    // get all posts
    @GetMapping("/get-all")
    public ResponseEntity<List<PostResponseDto>> getAllPosts(){
        List<PostResponseDto> postResponseDtoList = postService.getAllPosts();
        return new ResponseEntity<>(postResponseDtoList, HttpStatus.OK);
    }

    // update post
    @PutMapping("/update/{postId}")
    public ResponseEntity<PostResponseDto> updatePost(@Valid @RequestBody PostRequestDto postRequestDto, @PathVariable int postId) throws ResourceNotFoundException {
        PostResponseDto updatedPost = postService.updatePost(postRequestDto, postId);
        return new ResponseEntity<>(updatedPost, HttpStatus.OK);
    }

    // delete post
    @DeleteMapping("/delete/{postId}")
    public ResponseEntity<GeneralResponseDto> deletePost(@PathVariable int postId) throws ResourceNotFoundException {
        postService.deletePost(postId);
        return new ResponseEntity<>(new GeneralResponseDto("Post deleted successfully", true), HttpStatus.OK);
    }
}
