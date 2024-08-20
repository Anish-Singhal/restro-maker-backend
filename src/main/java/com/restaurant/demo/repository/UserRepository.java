package com.restaurant.demo.repository;

import com.restaurant.demo.dto.UserDto;
import com.restaurant.demo.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UserRepository extends JpaRepository<User,Long>{
    public List<User> findByUsernameAndPassword(String name,String password);
    @Query("select u from User u where u.username=:n and u.phone=:p")
    public List<User> findByUsernameAndPhone(@Param("n") String name,@Param("p") String phone);
    public User findByUsername(String name);

}
