package com.example.blog.blogappapis.Services;

import com.example.blog.blogappapis.Payloads.UserDto;

import java.util.List;

public interface UserService {

    UserDto saveUser(UserDto userDto);

    UserDto getUserById(Long userId);

    void deleteUser(Long userId);

    UserDto updateUser(UserDto userDto, Long userId);

    List<UserDto> getAllUsers();
}
