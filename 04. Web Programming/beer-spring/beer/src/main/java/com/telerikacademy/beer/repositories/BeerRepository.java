package com.telerikacademy.beer.repositories;

import com.telerikacademy.beer.models.Beer;

import java.util.List;

public interface BeerRepository {
    List<Beer> getAllBeers();

    Beer getBeerById(int id);

    Beer getBeerByName(String name);

    void createBeer(Beer beer);

    void updateBeer(Beer beer);

    void delete(int id);
}
