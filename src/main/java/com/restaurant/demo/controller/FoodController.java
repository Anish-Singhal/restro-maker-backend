package com.restaurant.demo.controller;

import com.restaurant.demo.dto.FoodDto;
import com.restaurant.demo.dto.RestaurantDto;
import com.restaurant.demo.dto.RestaurantIdDto;
import com.restaurant.demo.service.FoodService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/user/menu")
@AllArgsConstructor

public class FoodController {
    @Autowired
    private FoodService foodService;

    @PostMapping("/add")
    public ResponseEntity<List<FoodDto>> addFoodItems(@RequestParam Long rest_id, @RequestBody FoodDto foodDto){
        List<FoodDto> newItems = foodService.addFoodItems(rest_id,foodDto);
        return new ResponseEntity<>(newItems, HttpStatus.CREATED);
    }

    @GetMapping("/all")
    public ResponseEntity<List<FoodDto>> getAllFoodItems(@RequestParam Long rest_id){
        List<FoodDto> restaurantDto = foodService.getAllFoodItems(rest_id);
        return ResponseEntity.ok(restaurantDto);
    }
    @GetMapping("/categories")
    public ResponseEntity<List<String>> getAllCategories(@RequestParam Long rest_id){
        List<String> categories= foodService.getAllCategories(rest_id);
        return ResponseEntity.ok(categories);
    }

    @PostMapping("/remove/items")
    public ResponseEntity<List<FoodDto>> deleteItemsbyID(@RequestParam Long rest_id, @RequestBody Map<String,List<Long>> ItemsId) {
        List<FoodDto> savedItems = foodService.deleteItemsbyID(rest_id,ItemsId.get("itemsId"));
        return new ResponseEntity<>(savedItems, HttpStatus.CREATED);
    }

    @PostMapping("/remove/category")
    public ResponseEntity<List<FoodDto>> deleteItemsbyCategory(@RequestParam Long rest_id, @RequestBody Map<String,List<String>> Category) {
        List<FoodDto> savedItems = foodService.deleteItemsbyCategory(rest_id,Category.get("itemsId"));
        return new ResponseEntity<>(savedItems, HttpStatus.CREATED);
    }
}
