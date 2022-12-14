package com.example.blog.blogappapis.Services.Impl;

import com.example.blog.blogappapis.Entities.Category;
import com.example.blog.blogappapis.Entities.Post;
import com.example.blog.blogappapis.Entities.User;
import com.example.blog.blogappapis.Exceptions.ResourceNotFoundException;
import com.example.blog.blogappapis.Payloads.PageablePostResponse;
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
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.Comparator.comparingInt;
import static java.util.Comparator.comparingLong;
import static java.util.stream.Collectors.collectingAndThen;
import static java.util.stream.Collectors.toCollection;

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
    public PageablePostResponse getAllPost(int pageNumber, int pageSize, String sortBy, String sortDir) {
        Pageable pageable = sortDir.equalsIgnoreCase("desc") ? PageRequest.of(pageNumber,pageSize, Sort.by(sortBy).descending()) : PageRequest.of(pageNumber,pageSize, Sort.by(sortBy).ascending());

        Page<Post> posts = postRepo.findAll(pageable);

        List<Post> postList = posts.getContent();
        List<PostDto> postDto = posts.stream().map(post -> mapDto.postToPostDto(post)).toList();

        PageablePostResponse pageablePostResponse = new PageablePostResponse();
        pageablePostResponse.setLastPage(posts.isLast());
        pageablePostResponse.setPageSize(postDto.size());
        pageablePostResponse.setTotalPage(posts.getTotalPages());
        pageablePostResponse.setContent(postDto);
        pageablePostResponse.setTotalElements(posts.getTotalElements());
        pageablePostResponse.setPageNumber(posts.getNumber());
        return pageablePostResponse;
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
        List<Post> postList = postRepo.searchByTitle("%"+keyword+"%");

        //Custom search implementation

//        List<Post> postsFromContent = postRepo.findByTitleContaining(keyword);
//        List<Post> newList = Stream.concat(postsFromContent.stream(), postsFromTitle.stream()).toList();
//
//        List<Post> allPosts = newList.stream()
//                .collect(collectingAndThen(toCollection(() -> new TreeSet<>(comparingLong(Post::getPostId))),
//                        ArrayList::new));

        return postList.stream().map(post -> mapDto.postToPostDto(post)).collect(Collectors.toList());
    }
}
