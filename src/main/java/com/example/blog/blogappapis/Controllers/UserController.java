package com.example.blog.blogappapis.Controllers;

import com.example.blog.blogappapis.Payloads.ApiResponse;
import com.example.blog.blogappapis.Payloads.UserDto;
import com.example.blog.blogappapis.Services.Impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {

    @Autowired
    private UserServiceImpl userService;

    @PostMapping("/saveUser")
    public ResponseEntity<UserDto> saveUser(@Valid @RequestBody UserDto userDto){
        UserDto createdUser = userService.saveUser(userDto);
        return new ResponseEntity<>(createdUser, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDto> getUserById(@PathVariable("id") Long userId) throws RuntimeException{
        UserDto foundUser = userService.getUserById(userId);
        return new ResponseEntity<>(foundUser, HttpStatus.FOUND);
    }

    @PutMapping("/updateUser/{id}")
    public ResponseEntity<UserDto> updateUser(@RequestBody UserDto userDto, @PathVariable("id") Long userId) throws RuntimeException{
        UserDto updatedUser = userService.updateUser(userDto, userId);
        return ResponseEntity.ok(updatedUser);
    }

    @GetMapping("/getAllUsers")
    public ResponseEntity<List<UserDto>> getAllUsers (){
        List<UserDto> allUsers = userService.getAllUsers();
        return new ResponseEntity<>(allUsers,HttpStatus.FOUND);
    }

    @DeleteMapping("/deleteUser/{id}")
    public ResponseEntity<ApiResponse> deleteUser(@PathVariable("id") Long userId){
        userService.deleteUser(userId);
        return new ResponseEntity<>(new ApiResponse("User deleted Successfully", true), HttpStatus.OK);
    }
}
