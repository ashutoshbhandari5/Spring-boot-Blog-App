package com.example.blog.blogappapis.Controllers;
import com.example.blog.blogappapis.Config.AppConstants;
import com.example.blog.blogappapis.Entities.Post;
import com.example.blog.blogappapis.Payloads.ApiResponse;
import com.example.blog.blogappapis.Payloads.ListApiResponse;
import com.example.blog.blogappapis.Payloads.PageablePostResponse;
import com.example.blog.blogappapis.Payloads.PostDto;
import com.example.blog.blogappapis.Services.FileService;
import com.example.blog.blogappapis.Services.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/v1/post")
public class PostController {

    @Autowired
    private PostService postService;

    @Autowired
    private FileService fileService;

    @Value("project.image")
    private String path;

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
    public ResponseEntity<PageablePostResponse> getAllPost(@RequestParam(value = "pageNumber", defaultValue = AppConstants.PAGE_NUMBER, required = false) int pageNumber,
                                                           @RequestParam(value = "pageSize", defaultValue = AppConstants.PAGE_SIZE, required = false) int pageSize,
                                                           @RequestParam(value = "sortBy", required = false, defaultValue = AppConstants.SORT_BY) String sortBy,
                                                           @RequestParam(value = "sortDir", required = false, defaultValue = AppConstants.SORT_DIR) String sortDir)
    {
        PageablePostResponse postDto = postService.getAllPost(pageNumber, pageSize, sortBy, sortDir);
        return new ResponseEntity<>(postDto, HttpStatus.OK);
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

    //search Post
    @GetMapping("/searchPost/{keyword}")
    public ResponseEntity<ListApiResponse<List<PostDto>>> searchPost(@PathVariable("keyword") String keyword){
        List<PostDto> postDtoList = postService.searchPost(keyword);
        ListApiResponse<List<PostDto>> listApiResponse = new ListApiResponse<>(postDtoList.size(),true,postDtoList);
        return new ResponseEntity<>(listApiResponse,HttpStatus.OK);
    }

    @PostMapping("/uploadImage/{postId}")
    public ResponseEntity<PostDto> uploadPostImage(@RequestParam("image") MultipartFile file, @PathVariable("postId") Long postId) throws IOException {
        PostDto foundPost = postService.getPostById(postId);
        String imageName = fileService.uploadImage(path, file);
        foundPost.setImageName(imageName);
        PostDto postDto = postService.updatePost(postId,foundPost);
        return new ResponseEntity<>(postDto, HttpStatus.OK);
    }

}
