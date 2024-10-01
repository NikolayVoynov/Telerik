package com.telerikacademy.beer.repositories;

import com.telerikacademy.beer.exceptions.EntityNotFoundException;
import com.telerikacademy.beer.models.Beer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BeerMapRepositoryImpl implements BeerRepository {
    private Map<Integer, Beer> beers;

    public BeerMapRepositoryImpl() {
        this.beers = new HashMap<>();
    }

    @Override
    public List<Beer> getAllBeers() {
        return new ArrayList<>(beers.values());
    }

    @Override
    public Beer getBeerById(int id) {
        Beer beer = beers.get(id);

        if (beer == null) {
            throw new EntityNotFoundException("Beer", id);
        }

        return beer;
    }

    @Override
    public Beer getBeerByName(String name) {
        return beers
                .values()
                .stream()
                .filter(beer -> beer.getName().equals(name))
                .findFirst()
                .orElseThrow(() -> new EntityNotFoundException("Beer", "name", name));
    }

    @Override
    public void createBeer(Beer beer) {
        beers.put(beer.getId(), beer);
    }

    @Override
    public void updateBeer(Beer beer) {
        beers.replace(beer.getId(), beer);
    }

    @Override
    public void delete(int id) {
        beers.remove(id);
    }
}
