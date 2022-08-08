package com.example.blog.blogappapis.Services.Impl;

import com.example.blog.blogappapis.Entities.Category;
import com.example.blog.blogappapis.Entities.Post;
import com.example.blog.blogappapis.Entities.User;
import com.example.blog.blogappapis.Exceptions.ResourceNotFoundException;
import com.example.blog.blogappapis.Payloads.PostDto;
import com.example.blog.blogappapis.Repositories.CategoryRepo;
import com.example.blog.blogappapis.Repositories.PostRepo;
import com.example.blog.blogappapis.Repositories.UserRepo;
import com.example.blog.blogappapis.Services.PostService;
import com.example.blog.blogappapis.Utils.MapDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class PostServiceImpl implements PostService {

    @Autowired
    PostRepo postRepo;

    @Autowired
    UserRepo userRepo;

    @Autowired
    CategoryRepo categoryRepo;

    @Autowired
    MapDto mapDto;

    @Override
    public PostDto saveUser(PostDto postDto, Long userId, Long categoryId) {

        User foundUser = this.userRepo.findById(userId).orElseThrow(() -> new ResourceNotFoundException(String.format("User not found with user id:  %o", userId)));

        Category foundCategory = this.categoryRepo.findById(categoryId).orElseThrow(() -> new ResourceNotFoundException(String.format("Category not found with category id: %o", categoryId)));

       Post newPost = mapDto.postDtoToPost(postDto);
       newPost.setCategory(foundCategory);
       newPost.setUser(foundUser);
       newPost.setAddedDate(new Date());
       newPost.setImageName("default.png");

       Post createdPost = postRepo.save(newPost);

       return mapDto.postToPostDto(createdPost);
    }

    @Override
    public PostDto updatePost(Long postId, PostDto postDto) {
        return null;
    }

    @Override
    public PostDto deletePost(Long postId) {
        return null;
    }

    @Override
    public PostDto getPostById(Long postId) {
        return null;
    }

    @Override
    public List<Post> getAllPost() {
        return null;
    }

    @Override
    public List<Post> getAllPostByUser(Long userId) {
        return null;
    }

    @Override
    public List<Post> getAllPostByCategory(Long categoryId) {
        return null;
    }

    @Override
    public List<Post> searchPost(String keyword) {
        return null;
    }
}
