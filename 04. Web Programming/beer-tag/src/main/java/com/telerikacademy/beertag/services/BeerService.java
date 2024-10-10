package com.telerikacademy.beertag.services;

import com.telerikacademy.beertag.models.Beer;
import com.telerikacademy.beertag.models.User;

import java.util.List;

public interface BeerService {
    List<Beer> getAllBeers(String name, Double minAbv, Double maxAbv, Integer styleId, String sortBy, String sortOrderType);

    Beer getBeerById(int id);

    void createNewBeer(Beer beer, User user);

    void updateBeer(Beer beer, User user);

    void deleteBeer(int id,User user);

    Beer getBeerByName(String name);
}
