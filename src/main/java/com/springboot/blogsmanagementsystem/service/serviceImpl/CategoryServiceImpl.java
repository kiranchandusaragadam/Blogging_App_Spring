package com.springboot.blogsmanagementsystem.service.serviceImpl;

import com.springboot.blogsmanagementsystem.dto.requestDto.CategoryRequestDto;
import com.springboot.blogsmanagementsystem.dto.responseDto.CategoryResponseDto;
import com.springboot.blogsmanagementsystem.entity.Category;
import com.springboot.blogsmanagementsystem.exception.ResourceNotFoundException;
import com.springboot.blogsmanagementsystem.repository.CategoryRepository;
import com.springboot.blogsmanagementsystem.service.CategoryService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    CategoryRepository categoryRepository;

    @Autowired
    ModelMapper modelMapper;

    // add category
    @Override
    public CategoryResponseDto addCategory(CategoryRequestDto categoryRequestDto){
        Category category = modelMapper.map(categoryRequestDto, Category.class);
        // save category in database
        categoryRepository.save(category);
        return modelMapper.map(category, CategoryResponseDto.class);
    }

    // get category by id
    @Override
    public CategoryResponseDto getCategoryById(int catId) throws ResourceNotFoundException {
        // get category by id otherwise throw exception
        Category category = categoryRepository.findById(catId).orElseThrow(()->new ResourceNotFoundException("Category", "id", catId));

        return modelMapper.map(category, CategoryResponseDto.class);
    }

    // get all categories
    @Override
    public List<CategoryResponseDto> getAllCategories(){
        List<Category> categories = categoryRepository.findAll();
        // convert each category into category dto to return
        List<CategoryResponseDto> categoryDtos = categories.stream().map(category -> modelMapper.map(category, CategoryResponseDto.class)).collect(Collectors.toList());

        return categoryDtos;
    }

    // update category details by passing id
    @Override
    public CategoryResponseDto updateCategory(CategoryRequestDto categoryRequestDto, int catId) throws ResourceNotFoundException {
        // get category by id otherwise throw exception
        Category category = categoryRepository.findById(catId).orElseThrow(()->new ResourceNotFoundException("Category", "id", catId));

        category.setTitle(categoryRequestDto.getTitle());
        category.setDescription(categoryRequestDto.getDescription());
        // save the updated category
        Category updatedCategory = categoryRepository.save(category);

        return modelMapper.map(updatedCategory, CategoryResponseDto.class);
    }

    // delete category
    @Override
    public void deleteCategory(int catId) throws ResourceNotFoundException {
        // get category by id otherwise throw exception
        Category category = categoryRepository.findById(catId).orElseThrow(()->new ResourceNotFoundException("Category", "id", catId));

        categoryRepository.delete(category);
    }
}
