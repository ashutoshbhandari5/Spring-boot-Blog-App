package com.example.blog.blogappapis.Controllers;

import com.example.blog.blogappapis.Payloads.FileResponse;
import com.example.blog.blogappapis.Services.Impl.FileServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/api/v1/file")
public class FileController {

    @Autowired
    FileServiceImpl fileService;

    @Value(value = "${project.image}")
    private String path;

    @PostMapping("/upload")
    ResponseEntity<FileResponse> fileUpload(@RequestParam("image")MultipartFile file) throws IOException{
        String fileName = null;
        try {
            fileName = fileService.uploadImage(path, file);
        } catch (IOException e) {
            e.printStackTrace();
            return new ResponseEntity<>(new FileResponse(null, "File couldn't upload due to error"), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(new FileResponse(fileName, "File added successfully"), HttpStatus.OK);
    }
}
