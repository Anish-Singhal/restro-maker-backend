package com.restaurant.demo.entity;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString

public class JwtRequest {
    private String username;
    private String password;
}
