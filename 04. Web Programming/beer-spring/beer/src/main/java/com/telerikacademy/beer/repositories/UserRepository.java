package com.telerikacademy.beer.repositories;

import com.telerikacademy.beer.models.User;

import java.util.List;

public interface UserRepository {

    List<User> getAllUsers();

    User getUserById(int id);

    User getUserByName(String username);
}
