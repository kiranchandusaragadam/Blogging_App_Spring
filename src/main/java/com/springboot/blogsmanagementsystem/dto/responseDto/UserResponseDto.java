package com.springboot.blogsmanagementsystem.dto.responseDto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class UserResponseDto {
    private int id;
    private String name;
    private String email;
    private String password;
    private String about;
}
