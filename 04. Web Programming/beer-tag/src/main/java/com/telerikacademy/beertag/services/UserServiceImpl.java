package com.telerikacademy.beertag.services;

import com.telerikacademy.beertag.exceptions.EntityDuplicateException;
import com.telerikacademy.beertag.exceptions.EntityNotFoundException;
import com.telerikacademy.beertag.models.Beer;
import com.telerikacademy.beertag.models.User;
import com.telerikacademy.beertag.repositories.BeerRepository;
import com.telerikacademy.beertag.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final BeerRepository beerRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, BeerRepository beerRepository) {
        this.userRepository = userRepository;
        this.beerRepository = beerRepository;
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.getAllUsers();
    }

    @Override
    public User getUserById(int id) {
        return userRepository.getUserById(id);
    }

    @Override
    public User getUserByName(String username) {
        return userRepository.getUserByName(username);
    }

    @Override
    public void addBeerToWishList(int userId, int beerId) {
        User user = userRepository.getUserById(userId);

        if (user.getWishList().stream().anyMatch(beer -> beer.getId() == beerId)) {
            return;
        }

        Beer beer = beerRepository.getBeerById(beerId);
        user.getWishList().add(beer);
        userRepository.updateUser(user);
    }

    @Override
    public void removeFromWishList(int userId, int beerId) {
        User user = userRepository.getUserById(userId);

        if (user.getWishList().stream().noneMatch(beer -> beer.getId() == beerId)) {
            throw new EntityNotFoundException("Beer", beerId);
        }

        user.getWishList().removeIf(b -> b.getId() == beerId);
        userRepository.updateUser(user);
    }

    @Override
    public void createUser(User user) {
        boolean duplicateExists = true;

        try {
            userRepository.getUserByName(user.getUsername());
        } catch (EntityNotFoundException e) {
            duplicateExists = false;
        }

        if (duplicateExists) {
            throw new EntityDuplicateException("User", "username", user.getUsername());
        }

        userRepository.createUser(user);
    }


}
