package com.springboot.blogsmanagementsystem.dto.requestDto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class UserRequestDto {
    @NotEmpty
    private String name;
    @Email
    private String email;
    @NotEmpty
    @Size(min = 8, max = 15)
    private String password;
    @NotEmpty
    private String about;
}
