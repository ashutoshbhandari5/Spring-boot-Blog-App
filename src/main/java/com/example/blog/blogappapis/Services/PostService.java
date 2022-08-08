package com.example.blog.blogappapis.Services;
import com.example.blog.blogappapis.Entities.Post;
import com.example.blog.blogappapis.Payloads.CategoryDto;
import com.example.blog.blogappapis.Payloads.PostDto;
import com.example.blog.blogappapis.Payloads.UserDto;

import java.util.List;

public interface PostService {
    //create
    public PostDto saveUser(PostDto postDto, Long userId, Long categoryId);

    //update
    public PostDto updatePost(Long postId, PostDto postDto);

    //delete
    public PostDto deletePost(Long postId);

    //getById
    public PostDto getPostById(Long postId);

    //get All Post
    public List<Post> getAllPost();

    //getAllPostByUser
    public List<Post> getAllPostByUser(Long userId);

    //getAllPostByCategory
    public List<Post> getAllPostByCategory(Long categoryId);

    //search Post
    public List<Post> searchPost(String keyword);
}
