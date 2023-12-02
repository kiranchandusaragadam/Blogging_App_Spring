package com.springboot.blogsmanagementsystem.dto.responseDto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@NoArgsConstructor
@Getter
@Setter
public class PostResponseDto {
    private int id;
    private String title;
    private String content;
    private String imageName;
    private Date dateOfPost;
    private CategoryResponseDto category;
    private UserResponseDto user;
    private List<CommentResponseDto> commentList;
}
