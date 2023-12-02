package com.springboot.blogsmanagementsystem.exception;

public class ResourceNotFoundException extends Exception {
    public ResourceNotFoundException(String record, String field, int userId) {
        super(String.format("%s with %s : %s not found", record, field, userId));
    }
}
