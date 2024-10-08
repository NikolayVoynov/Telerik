package com.telerikacademy.beer.repositories;

import com.telerikacademy.beer.exceptions.EntityNotFoundException;
import com.telerikacademy.beer.models.Beer;

import java.util.ArrayList;
import java.util.List;

//@Repository
public class BeerRepositoryListImpl implements BeerRepository {

    private List<Beer> beers;

    public BeerRepositoryListImpl() {
        beers = new ArrayList<Beer>();

        beers.add(new Beer(1, "Glarus", 4.6));
        beers.add(new Beer(2, "Rhombus", 5.2));
        beers.add(new Beer(3, "Kamenitza", 4.2));
    }

    @Override
    public List<Beer> getAllBeers() {
        return beers;
    }

    @Override
    public Beer getBeerById(int id) {
        return beers
                .stream()
                .filter(beer -> beer.getId() == id)
                .findFirst()
                .orElseThrow(() -> new EntityNotFoundException("Beer", id));
    }

    @Override
    public Beer getBeerByName(String name) {
        return beers
                .stream()
                .filter(beer -> beer.getName().equals(name))
                .findFirst()
                .orElseThrow(() -> new EntityNotFoundException("Beer", "name", name));
    }

    @Override
    public void createBeer(Beer beer) {
        beers.add(beer);
    }

    @Override
    public void updateBeer(Beer beer) {
        Beer beerToUpdate = getBeerById(beer.getId());
        beerToUpdate.setName(beer.getName());
        beerToUpdate.setAbv(beer.getAbv());
    }

    @Override
    public void delete(int id) {
        Beer beerToDelete = getBeerById(id);
        beers.remove(beerToDelete);
    }
}
