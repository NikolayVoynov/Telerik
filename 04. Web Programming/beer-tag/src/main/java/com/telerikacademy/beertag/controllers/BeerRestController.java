package com.telerikacademy.beertag.controllers;

import com.telerikacademy.beertag.exceptions.AuthorizationException;
import com.telerikacademy.beertag.exceptions.EntityDuplicateException;
import com.telerikacademy.beertag.exceptions.EntityNotFoundException;
import com.telerikacademy.beertag.helpers.AuthenticationHelper;
import com.telerikacademy.beertag.helpers.BeerMapper;
import com.telerikacademy.beertag.models.Beer;
import com.telerikacademy.beertag.models.BeerDto;
import com.telerikacademy.beertag.models.FilterOptions;
import com.telerikacademy.beertag.models.User;
import com.telerikacademy.beertag.services.BeerService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/api/beers")
public class BeerRestController {

    private final BeerService beerService;
    private final BeerMapper beerMapper;
    private final AuthenticationHelper authenticationHelper;

    @Autowired
    public BeerRestController(BeerService beerService,
                              BeerMapper beerMapper,
                              AuthenticationHelper authenticationHelper) {
        this.beerService = beerService;
        this.beerMapper = beerMapper;
        this.authenticationHelper = authenticationHelper;
    }

    @GetMapping
    public List<Beer> getAllBeers(@RequestParam(required = false) String name,
                                  @RequestParam(required = false) Double minAbv,
                                  @RequestParam(required = false) Double maxAbv,
                                  @RequestParam(required = false) Integer styleId,
                                  @RequestParam(required = false) String sortBy,
                                  @RequestParam(required = false) String sortOrderType) {
        FilterOptions filterOptions = new FilterOptions(name, minAbv, maxAbv, styleId, sortBy, sortOrderType);
        return beerService.getAllBeers(filterOptions);
    }

    @GetMapping("/{id}")
    public Beer getBeerById(@PathVariable int id) {
        try {
            return beerService.getBeerById(id);

        } catch (EntityNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }

    @GetMapping("/search")
    public Beer getByName(@RequestParam String name) {
        try {
            return beerService.getBeerByName(name);
        } catch (EntityNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }

    @PostMapping
    public Beer createNewBeer(@RequestHeader HttpHeaders headers, @Valid @RequestBody BeerDto beerDto) {
        try {
            User user = authenticationHelper.tryGetUser(headers);
            Beer beer = beerMapper.dtoToBeer(beerDto);
            beerService.createNewBeer(beer, user);
            return beer;
        } catch (EntityNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        } catch (EntityDuplicateException e) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, e.getMessage());
        } catch (AuthorizationException e) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public Beer updateBeer(@RequestHeader HttpHeaders headers, @PathVariable int id, @Valid @RequestBody BeerDto beerDto) {
        try {
            User user = authenticationHelper.tryGetUser(headers);
            Beer beer = beerMapper.dtoToBeer(id, beerDto);
            beerService.updateBeer(beer, user);

            return beer;
        } catch (EntityNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        } catch (EntityDuplicateException e) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, e.getMessage());
        } catch (AuthorizationException e) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public void deleteBeer(@RequestHeader HttpHeaders headers, @PathVariable int id) {
        try {
            User user = authenticationHelper.tryGetUser(headers);
            beerService.deleteBeer(id, user);
        } catch (EntityNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        } catch (AuthorizationException e) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, e.getMessage());
        }
    }
}
