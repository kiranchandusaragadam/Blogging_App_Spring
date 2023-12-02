package com.springboot.blogsmanagementsystem.dto.responseDto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class CategoryResponseDto {
    private int id;
    private String title;
    private String description;
}
