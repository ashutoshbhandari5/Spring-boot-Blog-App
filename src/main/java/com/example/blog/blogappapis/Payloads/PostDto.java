package com.example.blog.blogappapis.Payloads;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@NoArgsConstructor
@Getter
@Setter
public class PostDto {

    private String title;
    private String content;
    private Long postId;

    private Date addedDate;
    private String imageName;
    private UserDto user;
    private CategoryDto category;
}
