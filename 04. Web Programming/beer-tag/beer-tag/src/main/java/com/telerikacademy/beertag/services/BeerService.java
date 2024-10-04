package com.telerikacademy.beertag.services;

import com.telerikacademy.beertag.models.Beer;

import java.util.List;

public interface BeerService {
    List<Beer> getAllBeers();

    Beer getBeerById(int id);

    void createNewBeer(Beer beer);

    void updateBeer(Beer beer);

    void deleteBeer(int id);
}
