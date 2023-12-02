package com.springboot.blogsmanagementsystem.service;

import com.springboot.blogsmanagementsystem.dto.requestDto.CategoryRequestDto;
import com.springboot.blogsmanagementsystem.dto.responseDto.CategoryResponseDto;
import com.springboot.blogsmanagementsystem.exception.ResourceNotFoundException;

import java.util.List;

public interface CategoryService {
    // add category
    CategoryResponseDto addCategory(CategoryRequestDto categoryRequestDto);

    // get category by id
    CategoryResponseDto getCategoryById(int catId) throws ResourceNotFoundException;

    // get all categories
    List<CategoryResponseDto> getAllCategories();

    // update category details by passing id
    CategoryResponseDto updateCategory(CategoryRequestDto categoryRequestDto, int catId) throws ResourceNotFoundException;

    // delete category
    void deleteCategory(int catId) throws ResourceNotFoundException;
}
