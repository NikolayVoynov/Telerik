package com.telerikacademy.beertag.services;

import com.telerikacademy.beertag.models.Beer;
import com.telerikacademy.beertag.models.User;

import java.util.List;

public interface UserService {

    List<User> getAllUsers();

    User getUserById(int id);

    User getUserByName(String username);

    void addBeerToWishList(int userId, int beerId);

    void removeFromWishList(int userId, int beerId);

    void createUser(User user);
}
