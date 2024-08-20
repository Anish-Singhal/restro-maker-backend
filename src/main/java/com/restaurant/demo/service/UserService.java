package com.restaurant.demo.service;

import com.restaurant.demo.dto.RestaurantDto;
import com.restaurant.demo.dto.UserDto;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.awt.image.BandCombineOp;
import java.util.List;

public interface UserService {
    UserDto createUser(UserDto userDto);
    UserDto getUserById(Long userId);
    UserDto getUserByUsername(String name);
    List<UserDto> getAllUser();
    UserDto updateUserByID(Long userId, UserDto details);
    void deleteUserById(Long userId);
    UserDto updateUserPassword(String name, UserDto details);
    List<UserDto> getUserByUsernameAndPhone(String name,String phone);
    List<UserDto> getUserByUsernameAndPassword(String name,String password);
}
