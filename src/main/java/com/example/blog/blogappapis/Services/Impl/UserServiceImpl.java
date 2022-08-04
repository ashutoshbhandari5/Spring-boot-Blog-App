package com.example.blog.blogappapis.Services.Impl;

import com.example.blog.blogappapis.Entities.User;
import com.example.blog.blogappapis.Exceptions.ResourceNotFoundException;
import com.example.blog.blogappapis.Payloads.UserDto;
import com.example.blog.blogappapis.Repositories.UserRepo;
import com.example.blog.blogappapis.Services.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

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
        Optional<User> user = userRepo.findById(userId);
        if(user.isEmpty()){
             throw new ResourceNotFoundException("User", "Id", userId);
        }
        return userToUserDto(user.get());
    }

    @Override
    public void deleteUser(Long userId) {

    }

    @Override
    public UserDto updateUser(UserDto userDto, Long userId) {
      Optional<User> user = userRepo.findById(userId);
      if(user.isEmpty()){
          throw new ResourceNotFoundException("User", "Id", userId);
      }
      if (Objects.nonNull(userDto.getName()) && !"".equalsIgnoreCase(userDto.getName())) {
          user.get().setName(userDto.getName());
      }
      if (Objects.nonNull(userDto.getAbout()) && !"".equalsIgnoreCase(userDto.getAbout())) {
          user.get().setName(userDto.getAbout());
      }
      if (Objects.nonNull(userDto.getEmail()) && !"".equalsIgnoreCase(userDto.getEmail())) {
          user.get().setName(userDto.getEmail());
      }
      userRepo.save(user.get());

     return userToUserDto(user.get());
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
