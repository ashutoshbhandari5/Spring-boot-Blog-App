package com.example.blog.blogappapis.Services.Impl;

import com.example.blog.blogappapis.Entities.User;
import com.example.blog.blogappapis.Exceptions.ResourceNotFoundException;
import com.example.blog.blogappapis.Payloads.UserDto;
import com.example.blog.blogappapis.Repositories.UserRepo;
import com.example.blog.blogappapis.Services.UserService;
import com.example.blog.blogappapis.Utils.MapDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private MapDto mapDto;

    @Override
    public UserDto saveUser(UserDto userDto) {
        User newUser = userRepo.save(mapDto.userDtoToUser(userDto));
        return mapDto.userToUserDto(newUser);
    }

    @Override
    public UserDto getUserById(Long userId) {
        Optional<User> user = userRepo.findById(userId);
        if(user.isEmpty()){
             throw new ResourceNotFoundException(String.format("User Id with %o not found in User", userId));
        }
        return mapDto.userToUserDto(user.get());
    }

    @Override
    public void deleteUser(Long userId) {
        Optional<User> user = userRepo.findById(userId);
        if(user.isEmpty()){
            throw new ResourceNotFoundException(String.format("User Id with %o not found in User", userId));
        }
        userRepo.deleteById(userId);
    }

    @Override
    public UserDto updateUser(UserDto userDto, Long userId) {
      Optional<User> user = userRepo.findById(userId);
      if(user.isEmpty()){
          throw new ResourceNotFoundException(String.format("User Id with %o not found in User", userId));
      }
      if (Objects.nonNull(userDto.getName()) && !"".equalsIgnoreCase(userDto.getName())) {
          user.get().setName(userDto.getName());
      }
      if (Objects.nonNull(userDto.getAbout()) && !"".equalsIgnoreCase(userDto.getAbout())) {
          user.get().setAbout(userDto.getAbout());
      }
      if (Objects.nonNull(userDto.getEmail()) && !"".equalsIgnoreCase(userDto.getEmail())) {
          user.get().setEmail(userDto.getEmail());
      }
      userRepo.save(user.get());

     return mapDto.userToUserDto(user.get());
    }

    @Override
    public List<UserDto> getAllUsers() {
        List<User> users= userRepo.findAll();
       return users.stream().map(user -> mapDto.userToUserDto(user)).collect(Collectors.toList());
    }
}
