package com.example.blog.blogappapis.Repositories;

import com.example.blog.blogappapis.Entities.Category;
import com.example.blog.blogappapis.Entities.Post;
import com.example.blog.blogappapis.Entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PostRepo extends JpaRepository<Post, Long> {

    public List<Post> findByUser(User user);
    public List<Post> findByCategory(Category category);

//    public List<Post> findByTitleContaining(String title);
//    public List<Post> findByContentContaining(String title);

   @Query(value = "select * from Post where title like :key or content like :key", nativeQuery = true)
    public List<Post> searchByTitle(@Param("key") String title);

}
