package com.telerikacademy.beertag.controllers;

import com.telerikacademy.beertag.exceptions.EntityDuplicateException;
import com.telerikacademy.beertag.exceptions.EntityNotFoundException;
import com.telerikacademy.beertag.models.Beer;
import com.telerikacademy.beertag.services.BeerService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/api/beers")
public class BeerRestController {

    BeerService beerService;

    @Autowired
    public BeerRestController(BeerService beerService) {
        this.beerService = beerService;
    }

    @GetMapping
    public List<Beer> getAllBeers() {
        return beerService.getAllBeers();
    }

    @GetMapping("/{id}")
    public Beer getBeerById(@PathVariable int id) {
        try {
            return beerService.getBeerById(id);

        } catch (EntityNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }

    @PostMapping
    public Beer createNewBeer(@Valid @RequestBody Beer beer) {
        try {
            beerService.createNewBeer(beer);
        } catch (EntityDuplicateException e) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, e.getMessage());
        }

        return beer;
    }

    @PutMapping("/{id}")
    public Beer updateBeer(@PathVariable int id, @Valid @RequestBody Beer beer) {
        try {
            beerService.updateBeer(beer);
        } catch (EntityNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        } catch (EntityDuplicateException e) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, e.getMessage());
        }

        return beer;
    }

    @DeleteMapping("/{id}")
    public void deleteBeer(@PathVariable int id) {
        try {
            beerService.deleteBeer(id);
        } catch (EntityNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }
}
