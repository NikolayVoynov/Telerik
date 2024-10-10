package com.telerikacademy.beertag.services;

import com.telerikacademy.beertag.exceptions.EntityDuplicateException;
import com.telerikacademy.beertag.exceptions.EntityNotFoundException;
import com.telerikacademy.beertag.exceptions.AuthorizationException;
import com.telerikacademy.beertag.models.Beer;
import com.telerikacademy.beertag.models.User;
import com.telerikacademy.beertag.repositories.BeerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BeerServiceImpl implements BeerService {

    private static final String MODIFY_BEER_ERROR_MESSAGE = "Only admin or beer creator can modify a beer.";

    private final BeerRepository beerRepository;

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
    public Beer getBeerByName(String name) {
        return beerRepository.getBeerByName(name);
    }


    @Override
    public void createNewBeer(Beer beer, User user) {
        boolean existsDuplicate = true;

        try {
            beerRepository.getBeerByName(beer.getName());
        } catch (EntityNotFoundException e) {
            existsDuplicate = false;
        }

        if (existsDuplicate) {
            throw new EntityDuplicateException("Beer", "name", beer.getName());
        } else {
            beer.setCreatedBy(user);
            beerRepository.createNewBeer(beer);
        }

    }


    @Override
    public void updateBeer(Beer beer, User user) {
        checkModifyPermissions(beer.getId(), user);

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
    public void deleteBeer(int id, User user) {
        checkModifyPermissions(id, user);
        beerRepository.deleteBeer(id);
    }

    private void checkModifyPermissions(int beerId, User user) {
        Beer beer = beerRepository.getBeerById(beerId);
        if (!(user.isAdmin() || beer.getCreatedBy().equals(user))) {
            throw new AuthorizationException(MODIFY_BEER_ERROR_MESSAGE);
        }
    }
}
