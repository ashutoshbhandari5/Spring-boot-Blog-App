package com.example.blog.blogappapis.Services;

import com.example.blog.blogappapis.Payloads.CategoryDto;

import java.util.List;

public interface CategoryService {
    //create
    CategoryDto saveCategory(CategoryDto categoryDto);

    //update
    CategoryDto updateCategory(Long categoryId, CategoryDto categoryDto);

    //delete
    void deleteCategory(Long categoryId);

    //get
    CategoryDto getCategoryById(Long categoryId);

    //getALL
    List<CategoryDto> getAllCategory();
}
