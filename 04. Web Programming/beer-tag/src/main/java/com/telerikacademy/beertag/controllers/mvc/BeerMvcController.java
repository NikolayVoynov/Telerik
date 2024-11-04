package com.telerikacademy.beertag.controllers.mvc;

import com.telerikacademy.beertag.exceptions.AuthenticationFailureException;
import com.telerikacademy.beertag.exceptions.AuthorizationException;
import com.telerikacademy.beertag.exceptions.EntityDuplicateException;
import com.telerikacademy.beertag.exceptions.EntityNotFoundException;
import com.telerikacademy.beertag.helpers.AuthenticationHelper;
import com.telerikacademy.beertag.helpers.BeerMapper;
import com.telerikacademy.beertag.models.*;
import com.telerikacademy.beertag.services.BeerService;
import com.telerikacademy.beertag.services.StyleService;
import com.telerikacademy.beertag.services.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/beers")
public class BeerMvcController {

    private final BeerService beerService;
    private final UserService userService;
    private final StyleService styleService;
    private final BeerMapper mapper;
    private final AuthenticationHelper authenticationHelper;

    @Autowired
    public BeerMvcController(BeerService beerService,
                             UserService userService,
                             StyleService styleService,
                             BeerMapper mapper,
                             AuthenticationHelper authenticationHelper) {
        this.beerService = beerService;
        this.userService = userService;
        this.styleService = styleService;
        this.mapper = mapper;
        this.authenticationHelper = authenticationHelper;
    }

    @ModelAttribute("requestURI")
    public String requestURI(final HttpServletRequest request) {
        return request.getRequestURI();
    }

    @ModelAttribute("styles")
    public List<Style> populateStyles() {
        return styleService.getAllStyles();
    }

    @GetMapping("/{id}")
    public String showSingleBeer(@PathVariable int id, Model model) {
        try {
            Beer beer = beerService.getBeerById(id);
            model.addAttribute("beer", beer);

            return "BeerView";
        } catch (EntityNotFoundException e) {
            model.addAttribute("statusCode", HttpStatus.NOT_FOUND.getReasonPhrase());
            model.addAttribute("error", e.getMessage());
            return "ErrorView";
        }
    }

    @GetMapping()
    public String showAllBeers(Model model) {
        model.addAttribute("beers",
                beerService.getAllBeers(new FilterOptions()));

        return "BeersView";
    }

    @GetMapping("/new")
    public String showNewBeerPage(Model model, HttpSession session) {
        try {
            authenticationHelper.tryGetUser(session);
        } catch (AuthenticationFailureException e) {
            return "redirect:/auth/login";
        }
        model.addAttribute("beer", new BeerDto());

        return "BeerCreateView";
    }

    @GetMapping("/{id}/update")
    public String showUpdateBeerPage(@PathVariable int id,
                                     Model model,
                                     HttpSession session) {
        try {
            authenticationHelper.tryGetUser(session);
        } catch (AuthenticationFailureException e) {
            return "redirect:/auth/login";
        }

        try {
            Beer beer = beerService.getBeerById(id);
            BeerDto beerDto = mapper.beerToDto(beer);
            model.addAttribute("beer", beerDto);

            return "BeerUpdateView";
        } catch (EntityNotFoundException e) {
            model.addAttribute("statusCode", HttpStatus.NOT_FOUND.getReasonPhrase());
            model.addAttribute("error", e.getMessage());
            return "ErrorView";
        }
    }

    @PostMapping("/new")
    public String createBeer(@Valid @ModelAttribute("beer") BeerDto beer,
                             BindingResult bindingResult,
                             Model model,
                             HttpSession session) {
        User user;
        try {
            user = authenticationHelper.tryGetUser(session);
        } catch (AuthenticationFailureException e) {
            return "redirect:/auth/login";
        }

        if (bindingResult.hasErrors()) {
            return "BeerCreateView";
        }

        try {
            Beer newBeer = mapper.dtoToBeer(beer);
            beerService.createNewBeer(newBeer, user);

            return "redirect:/beers";
        } catch (EntityDuplicateException e) {
            bindingResult.rejectValue("name", "beer.exists", e.getMessage());

            return "BeerCreateView";

        } catch (EntityNotFoundException e) {
            model.addAttribute("statusCode", HttpStatus.NOT_FOUND.getReasonPhrase());
            model.addAttribute("error", e.getMessage());
            return "ErrorView";
        }
    }

    @PostMapping("/{id}/update")
    public String updateBeer(@PathVariable int id, @Valid @ModelAttribute("beer") BeerDto beer,
                             BindingResult errors,
                             Model model,
                             HttpSession session) {
        User user;
        try {
            user = authenticationHelper.tryGetUser(session);
        } catch (AuthenticationFailureException e) {
            return "redirect:/auth/login";
        }

        if (errors.hasErrors()) {
            return "BeerUpdateView";
        }

        try {
            Beer newBeer = mapper.dtoToBeer(id, beer);
            beerService.updateBeer(newBeer, user);

            return "redirect:/beers";
        } catch (EntityNotFoundException e) {
            model.addAttribute("statusCode", HttpStatus.NOT_FOUND.getReasonPhrase());
            model.addAttribute("error", e.getMessage());

            return "ErrorView";
        } catch (AuthorizationException e) {
            model.addAttribute("statusCode", HttpStatus.UNAUTHORIZED.getReasonPhrase());
            model.addAttribute("error", e.getMessage());

            return "ErrorView";
        } catch (EntityDuplicateException e) {
            errors.rejectValue("name", "beer.exists", e.getMessage());

            return "BeerUpdateView";
        }
    }

    @GetMapping("/{id}/delete")
    public String deleteBeer(@PathVariable int id,
                             Model model,
                             HttpSession session) {
        User user;
        try {
            user = authenticationHelper.tryGetUser(session);
        } catch (AuthenticationFailureException e) {
            return "redirect:/auth/login";
        }

        try {
            beerService.deleteBeer(id, user);
            return "redirect:/beers";
        } catch (EntityNotFoundException e) {
            model.addAttribute("statusCode", HttpStatus.NOT_FOUND.getReasonPhrase());
            model.addAttribute("error", e.getMessage());

            return "ErrorView";
        } catch (AuthorizationException e) {
            model.addAttribute("statusCode", HttpStatus.UNAUTHORIZED.getReasonPhrase());
            model.addAttribute("error", e.getMessage());

            return "ErrorView";
        }
    }
}