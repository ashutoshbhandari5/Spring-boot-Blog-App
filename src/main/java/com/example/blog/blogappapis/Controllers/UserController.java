package com.example.blog.blogappapis.Controllers;

import com.example.blog.blogappapis.Payloads.UserDto;
import com.example.blog.blogappapis.Services.Impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {

    @Autowired
    private UserServiceImpl userService;

    @PostMapping("/saveUser")
    public UserDto saveUser(@RequestBody UserDto userDto){
        return userService.saveUser(userDto);
    }

    @GetMapping("/{id}")
    public UserDto getUserById(@PathVariable("id") Long userId) throws RuntimeException{
        return userService.getUserById(userId);
    }

    @PutMapping("/updateUser/{id}")
    public UserDto updateUser(@RequestBody UserDto userDto, @PathVariable("id") Long userId) throws RuntimeException{
        return userService.updateUser(userDto, userId);
    }
}
