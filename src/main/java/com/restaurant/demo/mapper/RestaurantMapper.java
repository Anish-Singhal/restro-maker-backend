package com.restaurant.demo.mapper;

import com.restaurant.demo.dto.RestaurantDto;
import com.restaurant.demo.entity.Restaurant;

public class RestaurantMapper {
    public static RestaurantDto mapToRestaurantDto(Restaurant restaurant){
        return new RestaurantDto(
                restaurant.getRest_id(),
                restaurant.getRest_name(),
                restaurant.getLocation(),
                restaurant.getFoodtype(),
                restaurant.getRest_rating(),
                restaurant.getItems()
        );
    }
    public static Restaurant mapToRestaurant(RestaurantDto restaurantDto){
        return new Restaurant(
                restaurantDto.getRest_id(),
                restaurantDto.getRest_name(),
                restaurantDto.getLocation(),
                restaurantDto.getFoodtype(),
                restaurantDto.getRest_rating(),
                restaurantDto.getItems()
        );
    }
}
