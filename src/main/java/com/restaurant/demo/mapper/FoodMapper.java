package com.restaurant.demo.mapper;

import com.restaurant.demo.dto.FoodDto;
import com.restaurant.demo.entity.FoodItems;

public class FoodMapper {
    public static FoodDto mapToFoodDto(FoodItems foodItems){
        return new FoodDto(
                foodItems.getItem_id(),
                foodItems.getFood_name(),
                foodItems.getFood_category(),
                foodItems.getFood_type(),
                foodItems.getPrice(),
                foodItems.getItem_available()
        );
    }
    public static FoodItems mapToFoodItems(FoodDto foodDto){
        return new FoodItems(
                foodDto.getItem_id(),
                foodDto.getItem_name(),
                foodDto.getFoodCategory(),
                foodDto.getRestFoodType(),
                foodDto.getPrice(),
                foodDto.getItem_available()
        );
    }
}
