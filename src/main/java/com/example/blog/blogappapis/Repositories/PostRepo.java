package com.example.blog.blogappapis.Repositories;

import com.example.blog.blogappapis.Entities.Category;
import com.example.blog.blogappapis.Entities.Post;
import com.example.blog.blogappapis.Entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostRepo extends JpaRepository<Post, Long> {

    public List<Post> findByUser(User user);
    public List<Post> findByCategory(Category category);

}
