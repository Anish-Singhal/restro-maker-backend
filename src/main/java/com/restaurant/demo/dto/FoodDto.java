package com.restaurant.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class FoodDto {
    private Long item_id;
    private String item_name;
    private String foodCategory;
    private String restFoodType;
    private float price;
    private String item_available;
}
