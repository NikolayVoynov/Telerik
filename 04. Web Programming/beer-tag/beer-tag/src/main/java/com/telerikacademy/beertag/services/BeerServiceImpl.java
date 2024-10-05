package com.telerikacademy.beertag.services;

import com.telerikacademy.beertag.exceptions.EntityDuplicateException;
import com.telerikacademy.beertag.exceptions.EntityNotFoundException;
import com.telerikacademy.beertag.models.Beer;
import com.telerikacademy.beertag.repositories.BeerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BeerServiceImpl implements BeerService {

    private BeerRepository beerRepository;

    @Autowired
    public BeerServiceImpl(BeerRepository beerRepository) {
        this.beerRepository = beerRepository;
    }


    @Override
    public List<Beer> getAllBeers(String name, Double minAbv, Double maxAbv, Integer styleId, String sortBy, String sortOrderType) {
        return beerRepository.getAllBeers(name, minAbv, maxAbv, styleId, sortBy, sortOrderType);
    }


    @Override
    public Beer getBeerById(int id) {
        return beerRepository.getBeerById(id);
    }


    @Override
    public void createNewBeer(Beer beer) {
        boolean existsDuplicate = true;

        try {
            beerRepository.getBeerByName(beer.getName());
        } catch (EntityNotFoundException e) {
            existsDuplicate = false;
        }

        if (existsDuplicate) {
            throw new EntityDuplicateException("Beer", "name", beer.getName());
        }

        beerRepository.createNewBeer(beer);
    }


    @Override
    public void updateBeer(Beer beer) {
        boolean existsDuplicate = true;

        try {
            Beer existingBeer = beerRepository.getBeerByName(beer.getName());

            if (existingBeer.getId() == beer.getId()) {
                existsDuplicate = false;
            }
        } catch (EntityNotFoundException e) {
            existsDuplicate = false;
        }

        if (existsDuplicate) {
            throw new EntityDuplicateException("Beer", "name", beer.getName());
        }

        beerRepository.updateBeer(beer);
    }


    @Override
    public void deleteBeer(int id) {
        beerRepository.deleteBeer(id);
    }
}
