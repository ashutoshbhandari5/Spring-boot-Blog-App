package com.example.blog.blogappapis.Controllers;

import com.example.blog.blogappapis.Payloads.FileResponse;
import com.example.blog.blogappapis.Services.Impl.FileServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

@RestController
@RequestMapping("/api/v1/file")
public class FileController {

    @Autowired
    FileServiceImpl fileService;

    @Value(value = "${project.image}")
    private String path;

    //upload file
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

    //download file
    @GetMapping(value = "/profile/{imageName}", produces = MediaType.IMAGE_JPEG_VALUE)
    public void downloadFile(@PathVariable("imageName") String imageName, HttpServletResponse response) throws IOException {
        InputStream inputStream = this.fileService.getResource(path,imageName);
        response.setContentType(MediaType.IMAGE_JPEG_VALUE);
        StreamUtils.copy(inputStream, response.getOutputStream());
    }
}
