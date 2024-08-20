package com.restaurant.demo.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

@Entity
@Table(name = "User_details",
        uniqueConstraints = {
        @UniqueConstraint(
                name = "unique_details",
                columnNames = "username")
        }
)

public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long user_id;
    @Column(name="username",nullable = false)
    private String username;

    @Column(name="password",nullable = false)
    private String password;

    @Column(name="phone",nullable = false)
    private String phone;

    @Column(name="email",nullable = false)
    private String email;

    @Column(name = "name")
    private String name;
    @Column(name = "Address")
    private String address;
    @Column(name="type")
    private String type;
    @Column(name = "Gender")
    private String gender;
    @Column(name = "cardId")
    private String cardId;

    @OneToMany(cascade = CascadeType.ALL,orphanRemoval = true)
    @JoinColumn(name = "owner_id",referencedColumnName = "user_id")
    private List<Restaurant> restaurants;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        SimpleGrantedAuthority simpleGrantedAuthority = new SimpleGrantedAuthority(type);
        return List.of(simpleGrantedAuthority);
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }
}
