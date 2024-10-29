package com.telerikacademy.beer.controllers.mvc;

import com.telerikacademy.beer.exceptions.EntityNotFoundException;
import com.telerikacademy.beer.models.Beer;
import com.telerikacademy.beer.services.BeerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.server.ResponseStatusException;

@Controller
@RequestMapping("/beers")
public class BeerMvcController {
    private BeerService beerService;

    @Autowired
    public BeerMvcController(BeerService beerService) {
        this.beerService = beerService;
    }

    @GetMapping("/{id}")
    public String showSingleBeer(@PathVariable int id, Model model) {
        try {
            Beer beer = beerService.getBeerById(id);
            model.addAttribute("beer", beer);

            return "beer";
        } catch (EntityNotFoundException e) {
            model.addAttribute("error", e.getMessage());
            return "not-found";
        }
    }

    @GetMapping
    public String showAllBeers(Model model) {
        model.addAttribute("beers", beerService.getAllBeers());

        return "beers";
    }
}
