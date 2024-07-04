package com.example.redis.Redis.Implementation.dao;

import com.example.redis.Redis.Implementation.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import java.util.Map;

@Repository
public class UserDao {

    @Autowired
    private RedisTemplate<String,Object> redisTemplate;
    private static final String KEY = "USER";

    public User saveUser(User user){
        redisTemplate.opsForHash().put(KEY,user.getUserId(),user);
        return user;
    }
    public User getUser(String userId){
        return (User) redisTemplate.opsForHash().get(KEY,userId);
    }
    //find all objects...........

    public Map<Object,Object> findAll(){
        return redisTemplate.opsForHash().entries(KEY);
    }
    // Delete operations
    public String delete(String userId){
        redisTemplate.opsForHash().delete(KEY,userId);
        return "Deleted";
    }
}
