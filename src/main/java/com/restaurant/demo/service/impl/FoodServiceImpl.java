package com.restaurant.demo.service.impl;

import com.restaurant.demo.dto.FoodDto;
import com.restaurant.demo.dto.RestaurantDto;
import com.restaurant.demo.entity.FoodItems;
import com.restaurant.demo.entity.Restaurant;
import com.restaurant.demo.entity.User;
import com.restaurant.demo.exception.ResourceNotFoundException;
import com.restaurant.demo.mapper.FoodMapper;
import com.restaurant.demo.mapper.RestaurantMapper;
import com.restaurant.demo.mapper.UserMapper;
import com.restaurant.demo.repository.FoodRepository;
import com.restaurant.demo.repository.RestaurantRepository;
import com.restaurant.demo.service.FoodService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class FoodServiceImpl implements FoodService {

    private RestaurantRepository restaurantRepository;
    private FoodRepository foodRepository;

    public List<FoodDto> addFoodItems(Long rest_id, FoodDto foodDto) {
        Restaurant restaurant = restaurantRepository.findById(rest_id).orElseThrow(()-> {
            return new ResourceNotFoundException("Restaurant does not exist with given ID: " + rest_id);
        });
        FoodItems foodItems = FoodMapper.mapToFoodItems(foodDto);
        restaurant.getItems().add(foodItems);
        restaurantRepository.save(restaurant);
        return (RestaurantMapper.mapToRestaurantDto(restaurant).getItems()).stream().map((items)->FoodMapper.mapToFoodDto(items)).collect(Collectors.toList());
    }

    public List<FoodDto> getAllFoodItems(Long rest_id){
        Restaurant restaurant = restaurantRepository.findById(rest_id).orElseThrow(()-> {
            return new ResourceNotFoundException("Restaurant does not exist with given ID: " + rest_id);
        });
        List<FoodItems> ItemList = restaurant.getItems();
        Collections.sort(ItemList,(f1,f2)->{return f1.getFood_name().compareTo(f2.getFood_name());});
        return ItemList.stream().map((items)->FoodMapper.mapToFoodDto(items)).collect(Collectors.toList());
    }

    public List<String> getAllCategories(Long rest_id){
        Restaurant restaurant = restaurantRepository.findById(rest_id).orElseThrow(()-> {
            return new ResourceNotFoundException("Restaurant does not exist with given ID: " + rest_id);
        });
        List<FoodItems> fooditems = restaurant.getItems();
        List<String> category = fooditems.stream().map(FoodItems::getFood_category).distinct().collect(Collectors.toList());
        return category;
    }

    public List<FoodDto> deleteItemsbyID(Long rest_id, List<Long> ItemsId){
        Restaurant restaurant = restaurantRepository.findById(rest_id).orElseThrow(()-> {
            return new ResourceNotFoundException("Restaurant does not exist with given ID: " + rest_id);
        });
        List<FoodItems> items = foodRepository.findAllById(ItemsId);
        for (FoodItems foodItem : items) {
            restaurant.getItems().remove(foodItem);
            foodRepository.delete(foodItem);
            restaurantRepository.save(restaurant);
        }
        return (restaurant.getItems()).stream().map((fooditems)->FoodMapper.mapToFoodDto(fooditems)).collect(Collectors.toList());
    }

    public List<FoodDto> deleteItemsbyCategory(Long rest_id, List<String> categories){
        Restaurant restaurant = restaurantRepository.findById(rest_id).orElseThrow(()-> {
            return new ResourceNotFoundException("Restaurant does not exist with given ID: " + rest_id);
        });
        for (String category : categories) {
            List<FoodItems> items = foodRepository.deleteByCategory(rest_id,category);;
            for (FoodItems foodItem : items) {
                restaurant.getItems().remove(foodItem);
                foodRepository.delete(foodItem);
                restaurantRepository.save(restaurant);
            }
        }
        return (restaurant.getItems()).stream().map((fooditems)->FoodMapper.mapToFoodDto(fooditems)).collect(Collectors.toList());
    }
}
