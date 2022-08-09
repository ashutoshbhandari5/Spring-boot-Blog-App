package com.example.blog.blogappapis.Controllers;
import com.example.blog.blogappapis.Payloads.ApiResponse;
import com.example.blog.blogappapis.Payloads.ListApiResponse;
import com.example.blog.blogappapis.Payloads.PostDto;
import com.example.blog.blogappapis.Services.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/post")
public class PostController {

    @Autowired
    private PostService postService;

    //save new post
    @PostMapping("/savePost/{userId}/{categoryId}")
    public ResponseEntity<PostDto> savePost(@PathVariable("userId") Long userId, @PathVariable("categoryId") Long categoryId, @RequestBody PostDto postDto){
        PostDto savedPostDto = postService.saveUser(postDto, userId, categoryId);
        return new ResponseEntity<>(savedPostDto, HttpStatus.CREATED);
    }

    @PutMapping("/updatePost/{postId}")
    public ResponseEntity<PostDto> updatePost(@PathVariable("postId") Long postId, @RequestBody PostDto postDto){
        PostDto updatedPostDto = postService.updatePost(postId, postDto);
        return new ResponseEntity<>(updatedPostDto, HttpStatus.OK);
    }

    @GetMapping("/{postId}")
    public ResponseEntity<PostDto> getPostById(@PathVariable("postId") Long postId){
        PostDto postDto = postService.getPostById(postId);
        return new ResponseEntity<>(postDto, HttpStatus.OK);
    }

    @GetMapping("/getAllPost")
    public ResponseEntity<ListApiResponse<List<PostDto>>> getAllPost(){
        List<PostDto> postDtoList = postService.getAllPost();
        ListApiResponse<List<PostDto>> listApiResponse = new ListApiResponse<List<PostDto>>(postDtoList.size(),true, postDtoList);
        return new ResponseEntity<>(listApiResponse, HttpStatus.OK);
    }

    @DeleteMapping("/deletePost/{postId}")
    public ResponseEntity<ApiResponse> deletePost(@PathVariable("postId") Long postId){
        ApiResponse apiResponse = new ApiResponse("Post deleted successfully", true);
        postService.deletePost(postId);
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }

    //find all post made by user
    @GetMapping("/getUserPosts/{userId}")
    public ResponseEntity<List<PostDto>> getUserPosts(@PathVariable("userId") Long userId){
        List<PostDto> postDto = postService.getAllPostByUser(userId);
        return new ResponseEntity<>(postDto,HttpStatus.OK);
    }

    //find all post from specific category
    @GetMapping("/getPostFromCategory/{categoryId}")
    public ResponseEntity<List<PostDto>> getPostFromCategory(@PathVariable("categoryId") Long categoryId){
        List<PostDto> postDtoList = postService.getAllPostByCategory(categoryId);
        return new ResponseEntity<>(postDtoList, HttpStatus.OK);
    }
}
