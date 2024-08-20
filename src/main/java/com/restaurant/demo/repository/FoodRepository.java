package com.restaurant.demo.repository;

import com.restaurant.demo.dto.FoodDto;
import com.restaurant.demo.entity.FoodItems;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface FoodRepository extends JpaRepository<FoodItems,Long> {

    @Query("Select f from Restaurant r INNER JOIN r.items f where r.rest_id = :id and f.food_category = :c")
    public List<FoodItems> deleteByCategory(@Param("id") Long rest_id,@Param("c") String category);
}
