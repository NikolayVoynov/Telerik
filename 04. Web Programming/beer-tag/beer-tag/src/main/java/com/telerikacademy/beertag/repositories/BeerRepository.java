package com.telerikacademy.beertag.repositories;

import com.telerikacademy.beertag.models.Beer;

import java.util.List;

public interface BeerRepository {
    List<Beer> getAllBeers();

    Beer getBeerById(int id);

    void createNewBeer(Beer beer);

    void updateBeer(Beer beer);

    void deleteBeer(int id);

    Beer getBeerByName(String name);
}
