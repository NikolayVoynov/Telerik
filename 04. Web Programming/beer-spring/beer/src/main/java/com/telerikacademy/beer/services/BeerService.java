package com.telerikacademy.beer.services;

import com.telerikacademy.beer.models.Beer;

import java.util.List;

public interface BeerService {
    List<Beer> getAllBeers();

    Beer getBeerById(int id);

    void createBeer(Beer beer);

    void updateBeer(Beer beer);

    void delete(int id);
}
