package com.restaurant.demo.controller;

import com.restaurant.demo.dto.UserDto;
import com.restaurant.demo.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/user")
@AllArgsConstructor
public class UserController {

    private UserService userService;

    @Autowired
    private PasswordEncoder passwordEncoder;
    @PostMapping("/signup")
    public ResponseEntity<UserDto> createUser(@RequestBody UserDto userDto){
        UserDto userSaved = userService.createUser(userDto);
        return new ResponseEntity<>(userSaved, HttpStatus.CREATED);
    }
    @PostMapping("/login2")
    public ResponseEntity<String> getUserByUsernameAndPassword(@RequestBody UserDto userDto){
        try{
            UserDto userFound = userService.getUserByUsername(userDto.getUsername());
            if(passwordEncoder.matches(userDto.getPassword(), userFound.getPassword())){
                return ResponseEntity.ok(userFound.getUsername());
            }
            else{
                throw new UsernameNotFoundException("Unauthorized");
            }
        }
        catch (Exception e){
            return ResponseEntity.ok(null);
        }
    }

    @PostMapping("/password")
    public ResponseEntity<List<UserDto>> getUserByUsernameAndPhone(@RequestBody UserDto userDto){
        List<UserDto> user1 = userService.getUserByUsernameAndPhone(userDto.getUsername(), userDto.getPhone());
        return ResponseEntity.ok(user1);
    }
    @GetMapping("{id}")
    public ResponseEntity<UserDto> getUserById(@PathVariable("id") Long userId){
        UserDto userFound = userService.getUserById(userId);
        return ResponseEntity.ok(userFound);
    }


    @GetMapping("/signup")
    public ResponseEntity<String> getUserByUsername(@RequestParam("username") String username){
        try{
            UserDto userFound = userService.getUserByUsername(username);
            return ResponseEntity.ok(userFound.getUsername());
        }
        catch (Exception e){
            return ResponseEntity.ok(null);
        }
    }
    @GetMapping
    public ResponseEntity<List<UserDto>> getAllUser(){
        List<UserDto> userList = userService.getAllUser();
        return ResponseEntity.ok(userList);
    }

    @PutMapping("{id}")
    public ResponseEntity<UserDto> updateUserById(@PathVariable("id") Long userId,@RequestBody UserDto userDto){
        UserDto userUpdated = userService.updateUserByID(userId,userDto);
        return ResponseEntity.ok(userUpdated);
    }
    @PutMapping("/password")
    public ResponseEntity<UserDto> updateUserPassword(@RequestBody UserDto userDto){
        UserDto userUpdated = userService.updateUserPassword(userDto.getUsername(),userDto);
        return ResponseEntity.ok(userUpdated);
    }
    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteUserById(@PathVariable("id") Long userId){
        userService.deleteUserById(userId);
        return ResponseEntity.ok("User deleted Successfully");
    }
}
