package com.telerikacademy.beertag.services;

import com.telerikacademy.beertag.models.Beer;

import java.util.List;

public interface BeerService {
    List<Beer> getAllBeers(String name, Double minAbv, Double maxAbv, Integer styleId, String sortBy, String sortOrderType);

    Beer getBeerById(int id);

    void createNewBeer(Beer beer);

    void updateBeer(Beer beer);

    void deleteBeer(int id);
}
