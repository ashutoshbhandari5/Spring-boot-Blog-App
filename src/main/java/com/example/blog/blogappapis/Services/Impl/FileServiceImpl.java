package com.example.blog.blogappapis.Services.Impl;

import com.example.blog.blogappapis.Exceptions.ResourceNotFoundException;
import com.example.blog.blogappapis.Services.FileService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
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
        final String substring = fileName.substring(fileName.length() - 4);
        if( !file.isEmpty() && !" ".equalsIgnoreCase(fileName) && fileName.length() > 5 && !substring.equals(".png")){
            String randomId = UUID.randomUUID().toString().concat(substring);

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
        }else {
            throw new ResourceNotFoundException("File name extension not found");
        }


    }

    @Override
    public InputStream getResource(String path, String filename) throws FileNotFoundException {
       String fullPath = path+File.separator+filename;
       return new FileInputStream(fullPath);
    }
}
