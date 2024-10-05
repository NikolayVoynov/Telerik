package com.telerikacademy.beertag.controllers;

import com.telerikacademy.beertag.exceptions.EntityDuplicateException;
import com.telerikacademy.beertag.exceptions.EntityNotFoundException;
import com.telerikacademy.beertag.helpers.BeerMapper;
import com.telerikacademy.beertag.models.Beer;
import com.telerikacademy.beertag.models.BeerDto;
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
    BeerMapper beerMapper;

    @Autowired
    public BeerRestController(BeerService beerService, BeerMapper beerMapper) {
        this.beerService = beerService;
        this.beerMapper = beerMapper;
    }

    @GetMapping
    public List<Beer> getAllBeers(@RequestParam(required = false) String name,
                                  @RequestParam(required = false) Double minAbv,
                                  @RequestParam(required = false) Double maxAbv,
                                  @RequestParam(required = false) Integer styleId,
                                  @RequestParam(required = false) String sortBy,
                                  @RequestParam(required = false) String sortOrderType) {
        return beerService.getAllBeers(name, minAbv, maxAbv, styleId, sortBy, sortOrderType);
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
    public Beer createNewBeer(@Valid @RequestBody BeerDto beerDto) {
        try {
            Beer beer = beerMapper.dtoToBeer(beerDto);
            beerService.createNewBeer(beer);
            return beer;
        } catch (EntityDuplicateException e) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public Beer updateBeer(@PathVariable int id, @Valid @RequestBody BeerDto beerDto) {
        try {
            Beer beer = beerMapper.dtoToBeer(id, beerDto);
            beerService.updateBeer(beer);

            return beer;
        } catch (EntityNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        } catch (EntityDuplicateException e) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, e.getMessage());
        }
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
