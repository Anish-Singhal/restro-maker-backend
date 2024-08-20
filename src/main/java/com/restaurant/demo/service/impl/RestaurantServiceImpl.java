package com.restaurant.demo.service.impl;

import com.restaurant.demo.dto.RestaurantDto;
import com.restaurant.demo.dto.UserDto;
import com.restaurant.demo.entity.Restaurant;
import com.restaurant.demo.entity.User;
import com.restaurant.demo.exception.ResourceNotFoundException;
import com.restaurant.demo.mapper.RestaurantMapper;
import com.restaurant.demo.mapper.UserMapper;
import com.restaurant.demo.repository.RestaurantRepository;
import com.restaurant.demo.repository.UserRepository;
import com.restaurant.demo.service.RestaurantService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class RestaurantServiceImpl implements RestaurantService {

    private UserRepository userRepository;

    private RestaurantRepository restaurantRepository;
    @Override
    public List<RestaurantDto> addRestaurant(String username, RestaurantDto restaurantDto) {
        User user = userRepository.findByUsername(username);
        if(user==null){
            throw new ResourceNotFoundException("User not present");
        }
        else {
            Restaurant restaurant = RestaurantMapper.mapToRestaurant(restaurantDto);
            user.getRestaurants().add(restaurant);
            userRepository.save(user);

            return (UserMapper.mapToUserDto(user).getRestaurants()).stream().map((rest)->RestaurantMapper.mapToRestaurantDto(rest)).collect(Collectors.toList());
        }
    }

    public List<RestaurantDto> getAllRestaurant(){
        List<Restaurant> restaurantList = restaurantRepository.findAll();
        return restaurantList.stream().map((restaurant)->RestaurantMapper.mapToRestaurantDto(restaurant)).collect(Collectors.toList());
    }
    public List<RestaurantDto> getAllRestaurantByUsername(String username){
        List<Restaurant> restaurantList = restaurantRepository.findAllByOwnername(username);
        return restaurantList.stream().map((restaurant)->RestaurantMapper.mapToRestaurantDto(restaurant)).collect(Collectors.toList());
    }
    public List<RestaurantDto> searchAllRestaurantByName(String username,String name){
        List<Restaurant> restaurantList = restaurantRepository.findAllRestaurantByName(username,name);
        return restaurantList.stream().map((restaurant)->RestaurantMapper.mapToRestaurantDto(restaurant)).collect(Collectors.toList());
    }

    public RestaurantDto updateRestaurantbyID(RestaurantDto details){
        Restaurant restaurant = restaurantRepository.findById(details.getRest_id()).orElseThrow(()-> {
            return new ResourceNotFoundException("Restaurant does not exist with given ID: " + details.getRest_id());
        });
        restaurant.setRest_name(details.getRest_name());
        Restaurant updatedRestaurant = restaurantRepository.save(restaurant);
        return RestaurantMapper.mapToRestaurantDto(restaurant);
    }

    public List<RestaurantDto> deleteRestaurantsbyID(String username,List<Long> restaurantIds){
        User user = userRepository.findByUsername(username);
        if(user==null){
            throw new ResourceNotFoundException("User not present");
        }
        else {
            List<Restaurant> restaurants = restaurantRepository.findAllById(restaurantIds);
            for (Restaurant restaurant : restaurants) {
                user.getRestaurants().remove(restaurant);
                restaurantRepository.delete(restaurant);
                userRepository.save(user);
            }
            return (UserMapper.mapToUserDto(user).getRestaurants()).stream().map((rest)->RestaurantMapper.mapToRestaurantDto(rest)).collect(Collectors.toList());
        }
    }

//    public RestaurantDto getRestaurantbyID(Long restaurantID){
//        Restaurant restaurant = restaurantRepository.findById(restaurantID).orElseThrow(()-> {
//            return new ResourceNotFoundException("Restaurant does not exist with given ID: " + restaurantID);
//        });
//        return RestaurantMapper.mapToRestaurantDto(restaurant);
//    }
}
