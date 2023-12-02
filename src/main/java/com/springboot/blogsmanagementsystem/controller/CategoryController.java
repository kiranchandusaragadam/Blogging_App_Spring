package com.springboot.blogsmanagementsystem.controller;

import com.springboot.blogsmanagementsystem.dto.requestDto.CategoryRequestDto;
import com.springboot.blogsmanagementsystem.dto.responseDto.CategoryResponseDto;
import com.springboot.blogsmanagementsystem.dto.responseDto.GeneralResponseDto;
import com.springboot.blogsmanagementsystem.exception.ResourceNotFoundException;
import com.springboot.blogsmanagementsystem.service.CategoryService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/categories")
public class CategoryController {
    @Autowired
    CategoryService categoryService;

    // add category
    @PostMapping("/add")
    public ResponseEntity<CategoryResponseDto> addCategory(@Valid @RequestBody CategoryRequestDto categoryRequestDto){
        CategoryResponseDto categoryDto = categoryService.addCategory(categoryRequestDto);
        return new ResponseEntity<>(categoryDto, HttpStatus.CREATED);
    }

    // get category by id
    @GetMapping("/get/{id}")
    public ResponseEntity<CategoryResponseDto> getCategoryById(@PathVariable("id") int catId) throws ResourceNotFoundException {
        CategoryResponseDto categoryDto = categoryService.getCategoryById(catId);
        return new ResponseEntity<>(categoryDto, HttpStatus.OK);
    }

    // get all categories
    @GetMapping("/get-all")
    public ResponseEntity<List<CategoryResponseDto>> getAllCategories(){
        List<CategoryResponseDto> categoryDtos = categoryService.getAllCategories();
        return new ResponseEntity<>(categoryDtos, HttpStatus.OK);
    }

    // update category by id
    @PutMapping("/update/{id}")
    public ResponseEntity<CategoryResponseDto> updateCategory(@Valid @RequestBody CategoryRequestDto categoryRequestDto, @PathVariable("id") int catId) throws ResourceNotFoundException {
        CategoryResponseDto categoryDto = categoryService.updateCategory(categoryRequestDto, catId);
        return new ResponseEntity<>(categoryDto, HttpStatus.OK);
    }

    // delete category by id
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<GeneralResponseDto> deleteCategory(@PathVariable("id") int catId) throws ResourceNotFoundException {
        categoryService.deleteCategory(catId);
        return new ResponseEntity<>(new GeneralResponseDto("Category deleted successfully", true), HttpStatus.OK);
    }
}
