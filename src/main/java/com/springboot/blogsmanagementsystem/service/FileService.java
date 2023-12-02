package com.springboot.blogsmanagementsystem.service;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface FileService {
    // upload image and return saved image name
    String uploadImage(String path, MultipartFile multipartFile) throws IOException;
}
