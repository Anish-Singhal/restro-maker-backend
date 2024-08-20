package com.restaurant.demo.repository;

import com.restaurant.demo.dto.RestaurantDto;
import com.restaurant.demo.entity.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface RestaurantRepository extends JpaRepository<Restaurant,Long>{
    @Query("Select r from User u INNER JOIN u.restaurants r where u.username = :n")
    public List<Restaurant> findAllByOwnername(@Param("n") String username);

    @Query("Select r from User u INNER JOIN u.restaurants r where u.username = :n and r.rest_name LIKE %:p%")
    public List<Restaurant> findAllRestaurantByName(@Param("n") String username,@Param("p") String name);
}
