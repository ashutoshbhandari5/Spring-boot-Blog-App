package com.example.blog.blogappapis.Services;


import org.springframework.web.multipart.MultipartFile;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

public interface FileService {

    public String uploadImage(String path, MultipartFile multipartFile) throws IOException;

    InputStream getResource(String path, String filename) throws FileNotFoundException;

}
