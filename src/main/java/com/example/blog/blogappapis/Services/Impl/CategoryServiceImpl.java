package com.example.blog.blogappapis.Services.Impl;

import com.example.blog.blogappapis.Entities.Category;
import com.example.blog.blogappapis.Payloads.CategoryDto;
import com.example.blog.blogappapis.Repositories.CategoryRepo;
import com.example.blog.blogappapis.Services.CategoryService;
import com.example.blog.blogappapis.Utils.MapDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryRepo categoryRepo;

    @Autowired
    MapDto mapDto;

    @Override
    public CategoryDto saveCategory(CategoryDto categoryDto) {
      Category category = categoryRepo.save(mapDto.categoryDtoToCategory(categoryDto));
      return mapDto.categoryToCategoryDto(category);
    }

    @Override
    public CategoryDto updateCategory(Long categoryId, CategoryDto categoryDto) {
        return null;
    }

    @Override
    public void deleteCategory(Long categoryId) {

    }

    @Override
    public CategoryDto getCategoryById(Long categoryId) {
        return null;
    }

    @Override
    public List<CategoryDto> getAllCategory() {
        return null;
    }
}
