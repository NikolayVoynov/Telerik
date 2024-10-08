package com.telerikacademy.beer.repositories;

import com.telerikacademy.beer.exceptions.EntityNotFoundException;
import com.telerikacademy.beer.models.User;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

//@Repository
public class UserRepositoryListImpl implements UserRepository {

    private final List<User> users;

    public UserRepositoryListImpl() {
        this.users = new ArrayList<>();

        users.add(new User(1, "nikolay", true));
        users.add(new User(2, "dimitar", false));
        users.add(new User(3, "monika", false));
    }

    @Override
    public List<User> getAllUsers() {
        return new ArrayList<>(users);
    }

    @Override
    public User getUserById(int id) {
        return users
                .stream()
                .filter(user -> user.getId() == id)
                .findFirst()
                .orElseThrow(() -> new EntityNotFoundException("User", id));
    }

    @Override
    public User getUserByName(String username) {
        return users
                .stream()
                .filter(user -> user.getUsername().equals(username))
                .findFirst()
                .orElseThrow(() -> new EntityNotFoundException("User", "username", username));
    }
}
