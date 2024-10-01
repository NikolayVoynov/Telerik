package com.telerikacademy.beer.services;

import com.telerikacademy.beer.exceptions.DuplicateEntityException;
import com.telerikacademy.beer.exceptions.EntityNotFoundException;
import com.telerikacademy.beer.models.Beer;
import com.telerikacademy.beer.repositories.BeerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BeerServiceImpl implements BeerService {

    private BeerRepository repository;

    @Autowired
    public BeerServiceImpl(BeerRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Beer> getAllBeers() {
        return repository.getAllBeers();
    }

    @Override
    public Beer getBeerById(int id) {
        return repository.getBeerById(id);
    }

    @Override
    public void createBeer(Beer beer) {
        boolean duplicateExists = true;

        try {
            repository.getBeerByName(beer.getName());
        } catch (EntityNotFoundException e) {
            duplicateExists = false;
        }

        if (duplicateExists) {
            throw new DuplicateEntityException("Beer", "name", beer.getName());
        }

        repository.createBeer(beer);
    }

    @Override
    public void updateBeer(Beer beer) {
        boolean duplicateExists = true;

        try {
            Beer existingBeer = repository.getBeerByName(beer.getName());

            if (existingBeer.getId() == beer.getId()) {
                duplicateExists = false;
            }
        } catch (EntityNotFoundException e) {
            duplicateExists = false;
        }

        if (duplicateExists) {
            throw new DuplicateEntityException("Beer", "name", beer.getName());
        }

        repository.updateBeer(beer);
    }

    @Override
    public void delete(int id) {
        repository.delete(id);
    }
}