package com.example.blog.blogappapis.Payloads;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ListApiResponse<T> {

    private Integer size;
    private Boolean success;
    private T payload;
}
