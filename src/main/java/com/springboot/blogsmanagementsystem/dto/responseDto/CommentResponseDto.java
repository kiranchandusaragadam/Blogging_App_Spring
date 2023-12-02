package com.springboot.blogsmanagementsystem.dto.responseDto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@NoArgsConstructor
@Getter
@Setter
public class CommentResponseDto {
    private int id;
    private String content;
    private Date commentDate;
    private String userName;
}
