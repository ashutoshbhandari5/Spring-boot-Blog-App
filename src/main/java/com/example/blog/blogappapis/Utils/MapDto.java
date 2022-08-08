package com.example.blog.blogappapis.Utils;

import com.example.blog.blogappapis.Entities.Category;
import com.example.blog.blogappapis.Entities.Post;
import com.example.blog.blogappapis.Entities.User;
import com.example.blog.blogappapis.Payloads.CategoryDto;
import com.example.blog.blogappapis.Payloads.PostDto;
import com.example.blog.blogappapis.Payloads.UserDto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MapDto {

    @Autowired
    private ModelMapper modelMapper;

    public User userDtoToUser (UserDto userDto){
        return modelMapper.map(userDto, User.class);
    }

    public UserDto userToUserDto (User user){
        return modelMapper.map(user, UserDto.class);
    }

    public Category categoryDtoToCategory(CategoryDto categoryDto){
        return modelMapper.map(categoryDto, Category.class);
    }

    public CategoryDto categoryToCategoryDto(Category category){
        return modelMapper.map(category, CategoryDto.class);
    }

    public PostDto postToPostDto(Post post){
        return modelMapper.map(post, PostDto.class);
    }

    public Post postDtoToPost(PostDto postDto){
        return modelMapper.map(postDto, Post.class);
    }
}
