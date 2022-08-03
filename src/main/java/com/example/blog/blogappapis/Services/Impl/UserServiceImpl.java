package com.example.blog.blogappapis.Services.Impl;

import com.example.blog.blogappapis.Entities.User;
import com.example.blog.blogappapis.Payloads.UserDto;
import com.example.blog.blogappapis.Repositories.UserRepo;
import com.example.blog.blogappapis.Services.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public UserDto saveUser(UserDto userDto) {
        User newUser = userRepo.save(userDtoToUser(userDto));
        return userToUserDto(newUser);
    }

    @Override
    public UserDto getUserById(Long userId) {
        return null;
    }

    @Override
    public void deleteUser(Long userId) {

    }

    @Override
    public UserDto updateUser(UserDto user) {
        return null;
    }

    @Override
    public List<UserDto> getAllUsers() {
        return null;
    }

    private User userDtoToUser (UserDto userDto){
      return modelMapper.map(userDto, User.class);
    }

    private UserDto userToUserDto (User user){
      return modelMapper.map(user, UserDto.class);
    }
}
