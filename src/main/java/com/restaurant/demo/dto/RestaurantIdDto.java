package com.restaurant.demo.dto;

import java.util.List;

public class RestaurantIdDto {
    private List<Long> restaurantIds;

    // getters and setters
    public List<Long> getRestaurantIds() {
        return restaurantIds;
    }

    public void setRestaurantIds(List<Long> restaurantIds) {
        this.restaurantIds = restaurantIds;
    }
}
