package com.telerikacademy.beertag.controllers.mvc;

import com.telerikacademy.beertag.exceptions.AuthorizationException;
import com.telerikacademy.beertag.exceptions.EntityNotFoundException;
import com.telerikacademy.beertag.helpers.AuthenticationHelper;
import com.telerikacademy.beertag.models.Beer;
import com.telerikacademy.beertag.models.User;
import com.telerikacademy.beertag.services.BeerService;
import com.telerikacademy.beertag.services.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Set;

@Controller
@RequestMapping("/users")
public class UserMvcController {

    private final AuthenticationHelper authenticationHelper;
    private final UserService userService;
    private final BeerService beerService;

    @Autowired
    public UserMvcController(AuthenticationHelper authenticationHelper,
                             UserService userService,
                             BeerService beerService) {
        this.authenticationHelper = authenticationHelper;
        this.userService = userService;
        this.beerService = beerService;
    }

    @PostMapping
    public String updateWishList(@PathVariable int beerId,
                                 Model model,
                                 HttpSession session) {
        try {
            User user;

            try {
                user = authenticationHelper.tryGetUser(session);
            } catch (AuthorizationException e) {
                return "redirect:/auth/login";
            }

            Beer beer = beerService.getBeerById(beerId);
            Set<Beer> wishList = user.getWishList();

            if (wishList.contains(beer)) {
                userService.removeFromWishList(user.getId(), beerId);
            } else {
                userService.addBeerToWishList(user.getId(), beerId);
            }

            return "redirect:/beers";

        } catch (EntityNotFoundException e) {
            model.addAttribute("statusCode", HttpStatus.NOT_FOUND.getReasonPhrase());
            model.addAttribute("error", e.getMessage());

            return "NotFoundView";
        }
    }
}
