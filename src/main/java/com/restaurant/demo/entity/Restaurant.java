package com.restaurant.demo.entity;

import jakarta.persistence.*;
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

@Entity
@Table(name = "restaurant")

public class Restaurant{
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long rest_id;

    @Column(name = "restaurant_name",nullable = false)
    private String rest_name;

    @Column(name = "location")
    private String location;

    @Column(name = "foodType")
    private String foodtype;

    @Column(name = "rest_rating")
    private int rest_rating;

    @OneToMany(cascade = CascadeType.ALL,orphanRemoval = true)
    @JoinColumn(name = "restaurant_id",referencedColumnName = "rest_id")
    private List<FoodItems> items;
}
