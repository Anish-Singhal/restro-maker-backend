package com.restaurant.demo.service.impl;

import com.restaurant.demo.dto.UserDto;
import com.restaurant.demo.entity.User;
import com.restaurant.demo.exception.ResourceNotFoundException;
import com.restaurant.demo.mapper.UserMapper;
import com.restaurant.demo.repository.UserRepository;
import com.restaurant.demo.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public UserDto createUser(UserDto userDto){
        User user = UserMapper.mapToUser(userDto);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        User newUser = userRepository.save(user);
        return UserMapper.mapToUserDto(newUser);
    }
    public List<UserDto> getUserByUsernameAndPassword(String username, String password){
        List<User> user = userRepository.findByUsernameAndPassword(username,password);
        if(user.size()==0){
            throw new ResourceNotFoundException("Invalid credentials");
        }
        return user.stream().map((u1)-> UserMapper.mapToUserDto(u1)).collect(Collectors.toList());
    }

    public List<UserDto> getUserByUsernameAndPhone(String username, String phone){
        List<User> user = userRepository.findByUsernameAndPhone(username,phone);
        if(user.size()==0){
            throw new ResourceNotFoundException("Invalid credentials");
        }
        return user.stream().map((u1)-> UserMapper.mapToUserDto(u1)).collect(Collectors.toList());
    }

    public UserDto getUserById(Long userId){
        User user = userRepository.findById(userId).orElseThrow(()->{
            return new ResourceNotFoundException("Restaurant does not exist with given ID: " + userId);
        });
        return UserMapper.mapToUserDto(user);
    }
    public UserDto getUserByUsername(String username){
        User user = userRepository.findByUsername(username);
        if(user==null){
            throw new ResourceNotFoundException("User not present");
        }
        return UserMapper.mapToUserDto(user);
    }
    public List<UserDto> getAllUser(){
        List<User> userList = userRepository.findAll();
        return userList.stream().map((user)->UserMapper.mapToUserDto(user)).collect(Collectors.toList());
    }
    public UserDto updateUserByID(Long userId, UserDto userDto){
        User user = userRepository.findById(userId).orElseThrow(()->{
            return new ResourceNotFoundException("Restaurant does not exist with given ID: " + userId);
        });
        user.setUsername(userDto.getUsername());
        user.setPassword(userDto.getPassword());
        user.setPhone(userDto.getPhone());
        user.setEmail(userDto.getEmail());
        user.setName(userDto.getName());
        user.setAddress(userDto.getAddress());
        user.setType(userDto.getType());
        user.setGender(userDto.getGender());
        user.setCardId(userDto.getCardId());
        User user1 = userRepository.save(user);
        return UserMapper.mapToUserDto(user1);
    }
    public UserDto updateUserPassword(String name, UserDto userDto){
        User user = userRepository.findByUsername(name);
        user.setPassword(userDto.getPassword());
        User user1 = userRepository.save(user);
        return UserMapper.mapToUserDto(user1);
    }
    public void deleteUserById(Long userId){
        User user = userRepository.findById(userId).orElseThrow(()->{
            return new ResourceNotFoundException("Restaurant does not exist with given ID: " + userId);
        });
        userRepository.deleteById(userId);
    }
}
