package com.restaurant.demo.mapper;

import com.restaurant.demo.dto.UserDto;
import com.restaurant.demo.entity.User;

public class UserMapper {
    public static UserDto mapToUserDto(User user){
        return new UserDto(
                user.getUser_id(),
                user.getUsername(),
                user.getPassword(),
                user.getPhone(),
                user.getEmail(),
                user.getName(),
                user.getAddress(),
                user.getType(),
                user.getGender(),
                user.getCardId(),
                user.getRestaurants()
        );
    }
    public static User mapToUser(UserDto userDto){
        return new User(
                userDto.getUser_id(),
                userDto.getUsername(),
                userDto.getPassword(),
                userDto.getPhone(),
                userDto.getEmail(),
                userDto.getName(),
                userDto.getAddress(),
                userDto.getType(),
                userDto.getGender(),
                userDto.getCardId(),
                userDto.getRestaurants()
        );
    }
}
