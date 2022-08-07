package com.example.blog.blogappapis.Services.Impl;

import com.example.blog.blogappapis.Entities.Category;
import com.example.blog.blogappapis.Exceptions.ResourceNotFoundException;
import com.example.blog.blogappapis.Payloads.CategoryDto;
import com.example.blog.blogappapis.Repositories.CategoryRepo;
import com.example.blog.blogappapis.Services.CategoryService;
import com.example.blog.blogappapis.Utils.MapDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

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
        Optional<Category> category = categoryRepo.findById(categoryId);
       if(category.isPresent()){

           if(Objects.nonNull(categoryDto.getCategoryTitle()) && !"".equalsIgnoreCase(categoryDto.getCategoryTitle())){
               category.get().setCategoryTitle(categoryDto.getCategoryTitle());
           }

           if(Objects.nonNull(categoryDto.getCategoryDescription()) && !"".equalsIgnoreCase(categoryDto.getCategoryDescription())){
               category.get().setCategoryDescription(categoryDto.getCategoryDescription());
           }
           categoryRepo.save(category.get());
           return mapDto.categoryToCategoryDto(category.get());
       }else {
           throw new ResourceNotFoundException(String.format("Category with category id: %o not found", categoryId));
       }
    }

    @Override
    public void deleteCategory(Long categoryId) {
        Optional<Category> category = categoryRepo.findById(categoryId);
        if(category.isEmpty()){
            throw new ResourceNotFoundException(String.format("Category with category id: %o not found", categoryId));
        }
        categoryRepo.deleteById(categoryId);
    }

    @Override
    public CategoryDto getCategoryById(Long categoryId) {
        Optional<Category> category = categoryRepo.findById(categoryId);
        if(category.isEmpty()){
            throw new ResourceNotFoundException(String.format("Category with category id: %o not found", categoryId));
        }
        return mapDto.categoryToCategoryDto(category.get());
    }

    @Override
    public List<CategoryDto> getAllCategory() {
        List<Category> categories = categoryRepo.findAll();

        return categories.stream().map(category -> mapDto.categoryToCategoryDto(category)).collect(Collectors.toList());
    }
}
