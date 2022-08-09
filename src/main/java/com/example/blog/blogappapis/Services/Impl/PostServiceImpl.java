package com.example.blog.blogappapis.Services.Impl;

import com.example.blog.blogappapis.Entities.Category;
import com.example.blog.blogappapis.Entities.Post;
import com.example.blog.blogappapis.Entities.User;
import com.example.blog.blogappapis.Exceptions.ResourceNotFoundException;
import com.example.blog.blogappapis.Payloads.CategoryDto;
import com.example.blog.blogappapis.Payloads.PostDto;
import com.example.blog.blogappapis.Repositories.CategoryRepo;
import com.example.blog.blogappapis.Repositories.PostRepo;
import com.example.blog.blogappapis.Repositories.UserRepo;
import com.example.blog.blogappapis.Services.PostService;
import com.example.blog.blogappapis.Utils.MapDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

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
        Post post = postRepo.findById(postId).orElseThrow(() -> new ResourceNotFoundException(String.format("Post with post id: %o not found",postId)));

        if(Objects.nonNull(postDto.getContent()) && !"".equalsIgnoreCase(postDto.getContent())){
            post.setContent(postDto.getContent());
        }

        if(Objects.nonNull(postDto.getTitle()) && !"".equalsIgnoreCase(postDto.getTitle())){
            post.setTitle(postDto.getTitle());
        }

        if(Objects.nonNull(postDto.getImageName()) && !"".equalsIgnoreCase(postDto.getImageName())){
            post.setImageName(postDto.getImageName());
        }

        postRepo.save(post);

        return mapDto.postToPostDto(post);
    }

    @Override
    public void deletePost(Long postId) {
        Post post = postRepo.findById(postId).orElseThrow(() -> new ResourceNotFoundException(String.format("Post with post id: %o not found",postId)));
        postRepo.deleteById(postId);
    }

    @Override
    public PostDto getPostById(Long postId) {
        Post post = postRepo.findById(postId).orElseThrow(() -> new ResourceNotFoundException(String.format("Post with post id: %o not found",postId)));
        return mapDto.postToPostDto(post);
    }

    @Override
    public List<PostDto> getAllPost(int pageNumber, int pageSize) {
        Pageable pageable = PageRequest.of(pageNumber,pageSize);
        List<Post> posts = postRepo.findAll(pageable).getContent();
        return posts.stream().map(post -> mapDto.postToPostDto(post)).collect(Collectors.toList());
    }

    @Override
    public List<PostDto> getAllPostByUser(Long userId) {
        User foundUser = userRepo.findById(userId).orElseThrow(() -> new ResourceNotFoundException(String.format("User not found with user id:  %o", userId)));
        List<Post> posts = postRepo.findByUser(foundUser);

        return posts.stream().map(post -> mapDto.postToPostDto(post)).collect(Collectors.toList());
    }

    @Override
    public List<PostDto> getAllPostByCategory(Long categoryId) {
        Category category = categoryRepo.findById(categoryId).orElseThrow(() -> new ResourceNotFoundException(String.format("Category not found with category id:  %o", categoryId)));
        List<Post> posts = postRepo.findByCategory(category);
        return posts.stream().map(post -> mapDto.postToPostDto(post)).collect(Collectors.toList());
    }

    @Override
    public List<PostDto> searchPost(String keyword) {
        return null;
    }
}
