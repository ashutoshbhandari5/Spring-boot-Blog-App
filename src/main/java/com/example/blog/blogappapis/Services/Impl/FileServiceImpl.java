package com.example.blog.blogappapis.Services.Impl;

import com.example.blog.blogappapis.Services.FileService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.UUID;

@Service
public class FileServiceImpl implements FileService {
    @Override
    public String uploadImage(String path, MultipartFile file) throws IOException{

        //get file name
        String fileName = file.getOriginalFilename();

        //full file path
        String randomId = UUID.randomUUID().toString().concat(fileName.substring(fileName.length()-4));

        String filePath = path+randomId;

        System.out.println(filePath);

        //Check if the file exists
        File f = new File(path);
        if(!f.exists()){
            f.mkdir();
        }

        //copy files
        Files.copy(file.getInputStream(), Paths.get(filePath));

        return fileName;
    }
}
