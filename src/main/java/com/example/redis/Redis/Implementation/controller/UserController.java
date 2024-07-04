package com.example.redis.Redis.Implementation.controller;


import com.example.redis.Redis.Implementation.model.User;
import com.example.redis.Redis.Implementation.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("user")
public class UserController {
    @Autowired
    UserService userService;



    @PostMapping("addUser")
    public ResponseEntity<User> addUser(@RequestBody User user){
        User data = userService.addUser(user);
        return new ResponseEntity<>(data, HttpStatus.OK);
    }

    @GetMapping("getUser/{id}")
    public ResponseEntity<User> getUser(@PathVariable String id){
        User data = userService.getUser(id);
        return new ResponseEntity<>(data, HttpStatus.OK);
    }

    @GetMapping("deleteUser/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable String id){
        userService.deleteUser(id);
        return new ResponseEntity<>("Success", HttpStatus.OK);
    }
    @GetMapping("findAll")
    public ResponseEntity<Map> findAll(){
        Map users = userService.findAll();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }
    @PutMapping("update/{id}")
    public ResponseEntity<User> update(@RequestBody User user, @PathVariable String id){
        return userService.update(user ,id);
    }
}
