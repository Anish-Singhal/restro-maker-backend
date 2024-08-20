package com.restaurant.demo.dto;

import com.restaurant.demo.entity.Restaurant;
import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class UserDto {
    private Long user_id;
    private String username;
    private String password;
    private String phone;
    private String email;
    private String name;
    private String address;
    private String type;
    private String gender;
    private String cardId;
    private List<Restaurant> restaurants;
}
