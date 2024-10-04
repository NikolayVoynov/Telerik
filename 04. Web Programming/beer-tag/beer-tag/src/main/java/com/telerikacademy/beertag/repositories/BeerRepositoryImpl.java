package com.telerikacademy.beertag.repositories;

import com.telerikacademy.beertag.exceptions.EntityNotFoundException;
import com.telerikacademy.beertag.models.Beer;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class BeerRepositoryImpl implements BeerRepository {
    private List<Beer> beers;

    public BeerRepositoryImpl() {
        this.beers = new ArrayList<>();

        beers.add(new Beer(1, "Zagorka", 4.5));
        beers.add(new Beer(2, "Tuborg", 4.2));
        beers.add(new Beer(3, "Bernard", 5.3));
        beers.add(new Beer(4, "Heineken", 4.8));
    }

    @Override
    public List<Beer> getAllBeers() {
        return beers;
    }

    @Override
    public Beer getBeerById(int id) {
        return getByIdHelper(id);
    }

    @Override
    public void createNewBeer(Beer beer) {
        beers.add(beer);
    }

    @Override
    public void updateBeer( Beer beer) {
        Beer beerToUpdate = getByIdHelper(beer.getId());

        beerToUpdate.setName(beer.getName());
        beerToUpdate.setAbv(beer.getAbv());
    }

    @Override
    public void deleteBeer(int id) {
        Beer beerToDelete = getByIdHelper(id);

        beers.remove(beerToDelete);
    }

    @Override
    public Beer getBeerByName(String name) {
        return beers
                .stream()
                .filter(beer -> beer.getName().equals(name))
                .findFirst()
                .orElseThrow(() -> new EntityNotFoundException("Beer", "name", name));
    }

    private Beer getByIdHelper(int id) {
        return beers.stream()
                .filter(beer -> beer.getId() == id)
                .findFirst()
                .orElseThrow(() -> new EntityNotFoundException("Beer", id));
    }
}
