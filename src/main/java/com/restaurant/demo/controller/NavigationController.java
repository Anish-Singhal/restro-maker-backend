package com.restaurant.demo.controller;

import com.restaurant.demo.security.JwtHelper;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/user/navigate")
@AllArgsConstructor
public class NavigationController {
    @Autowired
    private JwtHelper jwtHelper;

    @GetMapping("/restaurant")
    public ResponseEntity<String> chechUserRole(HttpServletRequest request){
        String requestHeader = request.getHeader("Authorization");
        String username = null;
        String token = null;
        if (requestHeader != null && requestHeader.startsWith("Bearer")) {
            token = requestHeader.substring(7);
        }
        System.out.println("Hello");
        return ResponseEntity.ok(token);
    }
}
