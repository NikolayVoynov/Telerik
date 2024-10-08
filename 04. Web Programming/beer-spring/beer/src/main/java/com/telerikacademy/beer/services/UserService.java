package com.telerikacademy.beer.services;

import com.telerikacademy.beer.models.User;

import java.util.List;

public interface UserService {

    List<User> getAllUsers();

    User getUserById(int id);

    User getUserByName(String username);
}
