package com.telerikacademy.beer.controllers;

import com.telerikacademy.beer.exceptions.DuplicateEntityException;
import com.telerikacademy.beer.exceptions.EntityNotFoundException;
import com.telerikacademy.beer.models.Beer;
import com.telerikacademy.beer.services.BeerService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/api/beers")
public class BeerController {

    private BeerService service;

    @Autowired
    public BeerController(BeerService service) {
        this.service = service;
    }

    @GetMapping
    public List<Beer> getAllBeers() {
        return service.getAllBeers();
    }

    @GetMapping("/{id}")
    public Beer getById(@PathVariable int id) {
        try {
            return service.getBeerById(id);
        } catch (EntityNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }

    @PostMapping
    public Beer create(@Valid @RequestBody Beer beer) {
        try {
            service.createBeer(beer);
        } catch (DuplicateEntityException e) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, e.getMessage());
        }

        return beer;
    }

    @PutMapping("/{id}")
    public Beer update(@PathVariable int id, @Valid @RequestBody Beer beer) {
        try {
            service.updateBeer(beer);
        } catch (EntityNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        } catch (DuplicateEntityException e) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, e.getMessage());
        }

        return beer;
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable int id) {
        try {
            service.delete(id);
        } catch (EntityNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }
}
