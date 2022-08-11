package com.example.blog.blogappapis.Payloads;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class FileResponse {

    private String fileName;
    private String message;
}
