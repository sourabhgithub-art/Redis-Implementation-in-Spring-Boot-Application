package com.example.redis.Redis.Implementation.service;

import com.example.redis.Redis.Implementation.dao.UserDao;
import com.example.redis.Redis.Implementation.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    UserDao userDao;

    public User addUser(User user) {
        userDao.saveUser(user);
        return user;
    }

    public User getUser(String id) {
        User user = userDao.getUser(id);
        return user;
    }

    public String deleteUser(String id) {
        userDao.delete(id);
        return "Success";
    }

    public Map findAll() {
        return userDao.findAll();
    }

    public ResponseEntity<User> update(User user, String id) {

        User existingUser = userDao.getUser(id);
        if (existingUser != null) {
            User userToUpdate = existingUser;
            // Update relevant fields (e.g., name, email, etc.)
            userToUpdate.setUserName(user.getUserName());
            userToUpdate.setUserAddress(user.getUserAddress());
            // Save the updated user back to Redis
            userDao.saveUser(userToUpdate);
            return ResponseEntity.ok(userToUpdate);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
