package com.restaurant.demo.service;

import com.restaurant.demo.dto.RestaurantDto;
import com.restaurant.demo.dto.UserDto;
import com.restaurant.demo.repository.RestaurantRepository;

import java.util.List;

public interface RestaurantService {

    //    RestaurantDto getRestaurantbyID(Long restaurantID);
    List<RestaurantDto> addRestaurant(String username, RestaurantDto restaurantDto);
    List<RestaurantDto> getAllRestaurant();
    List<RestaurantDto> getAllRestaurantByUsername(String username);
    List<RestaurantDto> searchAllRestaurantByName(String username,String name);
    RestaurantDto updateRestaurantbyID(RestaurantDto details);
    List<RestaurantDto> deleteRestaurantsbyID(String username,List<Long> restaurantIds);
}
