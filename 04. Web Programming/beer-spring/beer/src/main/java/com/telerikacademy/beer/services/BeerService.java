package com.telerikacademy.beer.services;

import com.telerikacademy.beer.models.Beer;
import com.telerikacademy.beer.models.User;

import java.util.List;

public interface BeerService {
    List<Beer> getAllBeers();

    Beer getBeerById(int id);

    Beer getBeerByName(String name);

    void createBeer(Beer beer);

    void updateBeer(Beer beer, User user);

    void delete(int id, User user);
}
