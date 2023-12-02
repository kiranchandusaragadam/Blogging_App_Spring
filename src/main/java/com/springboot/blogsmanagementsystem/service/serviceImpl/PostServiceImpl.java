package com.springboot.blogsmanagementsystem.service.serviceImpl;

import com.springboot.blogsmanagementsystem.dto.requestDto.PostRequestDto;
import com.springboot.blogsmanagementsystem.dto.responseDto.PostResponseDto;
import com.springboot.blogsmanagementsystem.entity.*;
import com.springboot.blogsmanagementsystem.exception.ResourceNotFoundException;
import com.springboot.blogsmanagementsystem.repository.CategoryRepository;
import com.springboot.blogsmanagementsystem.repository.PostRepository;
import com.springboot.blogsmanagementsystem.repository.UserRepository;
import com.springboot.blogsmanagementsystem.service.PostService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostServiceImpl implements PostService {
    @Autowired
    PostRepository postRepository;
    @Autowired
    CategoryRepository categoryRepository;
    @Autowired
    UserRepository userRepository;

    @Autowired
    ModelMapper modelMapper;

    // create and add post
    @Override
    public PostResponseDto addPost(PostRequestDto postRequestDto, int userId, int catId) throws ResourceNotFoundException {
        // get user and category if exists
        User user = userRepository.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User", "id", userId));
        // category
        Category category = categoryRepository.findById(catId).orElseThrow(()-> new ResourceNotFoundException("Category", "id", catId));

        // create post from post dto
        Post post = modelMapper.map(postRequestDto, Post.class);
        // now set remaining properties of post
        post.setDateOfPost(new Date());
        post.setCategory(category);
        post.setUser(user);

        postRepository.save(post);

        return modelMapper.map(post, PostResponseDto.class);
    }

    // get post by id
    @Override
    public PostResponseDto getPostById(int postId) throws ResourceNotFoundException {
        Post post = postRepository.findById(postId).orElseThrow(()-> new ResourceNotFoundException("Post", "id", postId));

        return modelMapper.map(post, PostResponseDto.class);
    }

    // get posts by category
    @Override
    public List<PostResponseDto> getPostsByCategory(int catId) throws ResourceNotFoundException {
        // first get category by id
        Category category = categoryRepository.findById(catId).orElseThrow(()-> new ResourceNotFoundException("Category", "id", catId));
        // get all posts related to this category
        List<Post> postList = postRepository.findByCategory(category);
        // convert post list to postdto list
        List<PostResponseDto> postResponseDtoList = postList.stream().map(post -> modelMapper.map(post, PostResponseDto.class)).collect(Collectors.toList());

        return postResponseDtoList;
    }

    // get posts by user
    @Override
    public List<PostResponseDto> getPostsByUser(int userId) throws ResourceNotFoundException {
        // first get category by id
        User user = userRepository.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User", "id", userId));
        // get all posts related to this user
        List<Post> postList = postRepository.findByUser(user);
        // convert post list to postdto list
        List<PostResponseDto> postResponseDtoList = postList.stream().map(post -> modelMapper.map(post, PostResponseDto.class)).collect(Collectors.toList());

        return postResponseDtoList;
    }

    // get all posts
    @Override
    public List<PostResponseDto> getAllPosts(){
        List<Post> postList = postRepository.findAll();
        // convert post list to postdto list
        List<PostResponseDto> postResponseDtoList = postList.stream().map(post -> modelMapper.map(post, PostResponseDto.class)).collect(Collectors.toList());

        return postResponseDtoList;
    }

    // update post details
    @Override
    public PostResponseDto updatePost(PostRequestDto postRequestDto, int postId) throws ResourceNotFoundException {
        // get the saved post
        Post post = postRepository.findById(postId).orElseThrow(()-> new ResourceNotFoundException("Post", "id", postId));
        // update details
        post.setTitle(postRequestDto.getTitle());
        post.setContent(postRequestDto.getContent());
        post.setImageName(postRequestDto.getImageName());
        post.setDateOfPost(new Date());

        postRepository.save(post);

        return modelMapper.map(post, PostResponseDto.class);
    }

    // delete post
    @Override
    public void deletePost(int postId) throws ResourceNotFoundException {
        // get the saved post
        Post post = postRepository.findById(postId).orElseThrow(()-> new ResourceNotFoundException("Post", "id", postId));

        postRepository.delete(post);
    }
}
