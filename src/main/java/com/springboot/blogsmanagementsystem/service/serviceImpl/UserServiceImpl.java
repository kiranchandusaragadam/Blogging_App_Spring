package com.springboot.blogsmanagementsystem.service.serviceImpl;

import com.springboot.blogsmanagementsystem.dto.requestDto.CommentRequestDto;
import com.springboot.blogsmanagementsystem.dto.requestDto.UserRequestDto;
import com.springboot.blogsmanagementsystem.dto.responseDto.CommentResponseDto;
import com.springboot.blogsmanagementsystem.dto.responseDto.GeneralResponseDto;
import com.springboot.blogsmanagementsystem.dto.responseDto.UserResponseDto;
import com.springboot.blogsmanagementsystem.entity.Comment;
import com.springboot.blogsmanagementsystem.entity.Post;
import com.springboot.blogsmanagementsystem.entity.User;
import com.springboot.blogsmanagementsystem.exception.ResourceNotFoundException;
import com.springboot.blogsmanagementsystem.repository.CommentRepository;
import com.springboot.blogsmanagementsystem.repository.PostRepository;
import com.springboot.blogsmanagementsystem.repository.UserRepository;
import com.springboot.blogsmanagementsystem.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserRepository userRepository;

    @Autowired
    PostRepository postRepository;

    @Autowired
    CommentRepository commentRepository;

    @Autowired
    ModelMapper modelMapper;

    @Override
    public UserResponseDto addUser(UserRequestDto userRequestDto){
        // create user object from given dto
        User user = modelMapper.map(userRequestDto, User.class);
        // save the user into database
        userRepository.save(user);

        return modelMapper.map(user, UserResponseDto.class);
    }

    @Override
    public UserResponseDto getUserById(int userId) throws ResourceNotFoundException {
        User user = userRepository.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User", "id", userId));

        return modelMapper.map(user, UserResponseDto.class);
    }

    @Override
    public List<UserResponseDto> getAllUsers(){
        // get all users
        List<User> users = userRepository.findAll();
        // convert all users to user response dtos
        List<UserResponseDto> userDtos = users.stream().map(user -> modelMapper.map(user, UserResponseDto.class)).collect(Collectors.toList());
        // return
        return userDtos;
    }

    @Override
    public UserResponseDto updateUserById(UserRequestDto userRequestDto, int userId) throws ResourceNotFoundException {
        User user = userRepository.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User", "id", userId));
        // update the user
        user.setName(userRequestDto.getName());
        user.setEmail(userRequestDto.getEmail());
        user.setPassword(userRequestDto.getPassword());
        user.setAbout(userRequestDto.getAbout());
        // save
        User updatedUser = userRepository.save(user);
        return modelMapper.map(updatedUser, UserResponseDto.class);
    }

    @Override
    public void deleteUserById(int userId) throws ResourceNotFoundException {
        User user = userRepository.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User", "id", userId));
        // delete user from database
        userRepository.delete(user);
    }

    // comment on post by passing post id, user id
    @Override
    public CommentResponseDto commentOnPost(CommentRequestDto commentRequestDto, int userId, int postId) throws ResourceNotFoundException {
        // get user
        User user = userRepository.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User", "id", userId));
        // get post
        Post post = postRepository.findById(postId).orElseThrow(()-> new ResourceNotFoundException("Post", "id", postId));
        // now create comment on this post
        Comment comment = modelMapper.map(commentRequestDto, Comment.class);
        comment.setCommentDate(new Date());
        comment.setUser(user);
        comment.setPost(post);
        // save
        commentRepository.save(comment);

        return modelMapper.map(comment, CommentResponseDto.class);
    }

    // delete comment by that user in corresponding post
    @Override
    public GeneralResponseDto deleteComment(int userId, int postId, int commentId) throws ResourceNotFoundException {
        // get the comment
        Comment comment = commentRepository.findById(commentId).orElseThrow(()-> new ResourceNotFoundException("Comment", "id", commentId));
        // now validate given user and post relation with this comment
        int commentedUserId = comment.getUser().getId();
        int commentedPostId = comment.getPost().getId();

        if(userId != commentedUserId){
            return new GeneralResponseDto("This comment not belongs to this user", false);
        }
        else if(postId != commentedPostId){
            return new GeneralResponseDto("This comment not belongs to this post", false);
        }

        commentRepository.delete(comment);
        return new GeneralResponseDto("Comment deleted successfully", true);
    }
}
