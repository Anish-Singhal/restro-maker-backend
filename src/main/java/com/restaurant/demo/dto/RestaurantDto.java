package com.restaurant.demo.dto;

import com.restaurant.demo.entity.FoodItems;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.boot.autoconfigure.web.WebProperties;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class RestaurantDto {
    private Long rest_id;
    private String rest_name;
    private String location;
    private String foodtype;
    private int rest_rating;
    private List<FoodItems> items;
}
