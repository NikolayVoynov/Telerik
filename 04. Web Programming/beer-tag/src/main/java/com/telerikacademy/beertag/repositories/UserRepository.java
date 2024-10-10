package com.telerikacademy.beertag.repositories;

import com.telerikacademy.beertag.models.User;

import java.util.List;

public interface UserRepository {

    List<User> getAllUsers();

    User getUserById(int id);

    User getUserByName(String username);

    void updateUser(User user);
}
