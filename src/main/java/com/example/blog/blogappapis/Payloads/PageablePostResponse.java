package com.example.blog.blogappapis.Payloads;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@NoArgsConstructor
@Getter
@Setter
public class PageablePostResponse {

    private boolean lastPage;
    private int totalPage;
    private Long totalElements;
    private int pageSize;
    private int pageNumber;
    List<PostDto> content;
}
