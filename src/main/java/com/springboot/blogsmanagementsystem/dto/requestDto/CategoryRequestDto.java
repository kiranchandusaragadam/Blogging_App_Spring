package com.springboot.blogsmanagementsystem.dto.requestDto;

import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class CategoryRequestDto {
    @NotEmpty
    private String title;
    @NotEmpty
    private String description;
}
