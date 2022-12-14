package com.example.blog.blogappapis.Services;
import com.example.blog.blogappapis.Payloads.PageablePostResponse;
import com.example.blog.blogappapis.Payloads.PostDto;

import java.util.List;

public interface PostService {
    //create
    public PostDto saveUser(PostDto postDto, Long userId, Long categoryId);

    //update
    public PostDto updatePost(Long postId, PostDto postDto);

    //delete
    public void deletePost(Long postId);

    //getById
    public PostDto getPostById(Long postId);

    //get All Post
    public PageablePostResponse getAllPost(int pageNumber, int pageSize, String sortBy, String sortDir);

    //getAllPostByUser
    public List<PostDto> getAllPostByUser(Long userId);

    //getAllPostByCategory
    public List<PostDto> getAllPostByCategory(Long categoryId);

    //search Post
    public List<PostDto> searchPost(String keyword);
}
