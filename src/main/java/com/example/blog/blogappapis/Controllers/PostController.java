package com.example.blog.blogappapis.Controllers;

import com.example.blog.blogappapis.Entities.Post;
import com.example.blog.blogappapis.Payloads.PostDto;
import com.example.blog.blogappapis.Services.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/post")
public class PostController {

    @Autowired
    private PostService postService;

    @PostMapping("/savePost/{userId}/{categoryId}")
    public ResponseEntity<PostDto> savePost(@PathVariable("userId") Long userId, @PathVariable("categoryId") Long categoryId, @RequestBody PostDto postDto){
        PostDto savedPostDto = postService.saveUser(postDto, userId, categoryId);
        return new ResponseEntity<>(savedPostDto, HttpStatus.CREATED);
    }
}
