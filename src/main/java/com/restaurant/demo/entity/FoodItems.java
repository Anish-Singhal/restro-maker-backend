package com.restaurant.demo.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

@Entity
@Table(name = "fooditems")

public class FoodItems{
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long item_id;

    @Column(name = "food_name",nullable = false)
    private String food_name;

    @Column(name = "food_category")
    private String food_category;

    @Column(name = "food_type")
    private String food_type;

    @Column(name = "item_price")
    private float price;

    @Column(name = "available")
    private String item_available;
}
