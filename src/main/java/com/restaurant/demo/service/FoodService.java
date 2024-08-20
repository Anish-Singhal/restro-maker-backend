package com.restaurant.demo.service;

import com.restaurant.demo.dto.FoodDto;
import com.restaurant.demo.dto.RestaurantDto;

import java.util.List;
import java.util.Map;

public interface FoodService {
    List<FoodDto> addFoodItems(Long rest_id,FoodDto foodDto);
    List<FoodDto> getAllFoodItems(Long rest_id);
    List<FoodDto> deleteItemsbyID(Long rest_id, List<Long> ItemsId);
    List<String> getAllCategories(Long rest_id);
    List<FoodDto> deleteItemsbyCategory(Long rest_id, List<String> category);
}
