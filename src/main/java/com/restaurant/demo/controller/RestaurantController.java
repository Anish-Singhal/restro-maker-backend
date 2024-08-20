package com.restaurant.demo.controller;

import com.restaurant.demo.dto.RestaurantDto;
import com.restaurant.demo.dto.RestaurantIdDto;
import com.restaurant.demo.security.JwtHelper;
import com.restaurant.demo.service.RestaurantService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/user/restaurant")
@AllArgsConstructor
public class RestaurantController {
    private RestaurantService restaurantService;
    @Autowired
    private JwtHelper jwtHelper;

    @PostMapping("/new")
    public ResponseEntity<List<RestaurantDto>> addRestaurant(@RequestBody RestaurantDto restaurantDto, @RequestHeader("Authorization") String token){
        if (token.startsWith("Bearer ")) {
            token = token.substring(7);
        }
        String username = jwtHelper.getUsernameFromToken(token);
        List<RestaurantDto> savedRestaurant = restaurantService.addRestaurant(username,restaurantDto);
        return new ResponseEntity<>(savedRestaurant, HttpStatus.CREATED);
    }

    @GetMapping("/userpage")
    public ResponseEntity<List<RestaurantDto>> getAllRestaurant(){
        List<RestaurantDto> restaurantDto = restaurantService.getAllRestaurant();
        return ResponseEntity.ok(restaurantDto);
    }

    @GetMapping("/all")
    public ResponseEntity<List<RestaurantDto>> getAllRestaurantByUsername(@RequestHeader("Authorization") String token){
        if (token.startsWith("Bearer ")) {
            token = token.substring(7);
        }
        String username = jwtHelper.getUsernameFromToken(token);
        List<RestaurantDto> restaurantDto = restaurantService.getAllRestaurantByUsername(username);
        return ResponseEntity.ok(restaurantDto);
    }

    // For search restaurant option.
    @GetMapping("/{rest_name}")
    public ResponseEntity<List<RestaurantDto>> searchAllRestaurantByUserName(@RequestHeader("Authorization") String token, @PathVariable("rest_name") String name){
        if (token.startsWith("Bearer ")) {
            token = token.substring(7);
        }
        String username = jwtHelper.getUsernameFromToken(token);
        List<RestaurantDto> restaurantDto = restaurantService.searchAllRestaurantByName(username,name);
        return ResponseEntity.ok(restaurantDto);
    }

    @PostMapping("/remove")
    public ResponseEntity<List<RestaurantDto>> deleteRestaurantsbyID(@RequestBody RestaurantIdDto restaurantIdDto,@RequestHeader("Authorization") String token){
        if (token.startsWith("Bearer ")) {
            token = token.substring(7);
        }
        String username = jwtHelper.getUsernameFromToken(token);
        List<Long> restaurantIds = restaurantIdDto.getRestaurantIds();
        List<RestaurantDto> savedRestaurant = restaurantService.deleteRestaurantsbyID(username,restaurantIds);
        return new ResponseEntity<>(savedRestaurant, HttpStatus.CREATED);
//        return ResponseEntity.ok("Restaurant deleted Successfully");
    }

//    @GetMapping("/id")
//    public ResponseEntity<RestaurantDto> getRestaurantbyID(@PathVariable("id") Long restaurantID){
//        RestaurantDto restaurantDto = restaurantService.getRestaurantbyID(restaurantID);
//        return ResponseEntity.ok(restaurantDto);
//    }

    @PutMapping("/rename")
    public ResponseEntity<RestaurantDto> updateRestaurantbyID(@RequestBody RestaurantDto details,@RequestHeader("Authorization") String token){
        if (token.startsWith("Bearer ")) {
            token = token.substring(7);
        }
        String username = jwtHelper.getUsernameFromToken(token);
        RestaurantDto restaurantDto = restaurantService.updateRestaurantbyID(details);
        return ResponseEntity.ok(restaurantDto);
    }
}
