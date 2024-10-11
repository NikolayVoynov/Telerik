package com.telerikacademy.beertag.controllers;

import com.telerikacademy.beertag.exceptions.AuthorizationException;
import com.telerikacademy.beertag.exceptions.EntityNotFoundException;
import com.telerikacademy.beertag.helpers.AuthenticationHelper;
import com.telerikacademy.beertag.models.Beer;
import com.telerikacademy.beertag.models.User;
import com.telerikacademy.beertag.services.UserService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserRestController {

    public static final String AUTHORIZATION_ERROR = "You are not authorized to browse user information.";
    private final UserService userService;
    private final AuthenticationHelper authenticationHelper;

    public UserRestController(UserService userService, AuthenticationHelper authenticationHelper) {
        this.userService = userService;
        this.authenticationHelper = authenticationHelper;
    }

    @GetMapping
    public List<User> getAllUsers(@RequestHeader HttpHeaders headers) {
        try {
            User user = authenticationHelper.tryGetUser(headers);

            if (!user.isAdmin()) {
                throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, AUTHORIZATION_ERROR);
            }

            return userService.getAllUsers();
        } catch (AuthorizationException e) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, e.getMessage());
        }
    }

    @GetMapping("/{id}")
    public User getUserById(@RequestHeader HttpHeaders headers, @PathVariable int id) {
        try {
            User user = authenticationHelper.tryGetUser(headers);
            checkPermissionsForAccess(id, user);

            return userService.getUserById(id);
        } catch (EntityNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        } catch (AuthorizationException e) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, e.getMessage());
        }
    }

    @GetMapping("/{id}/wish-list")
    public List<Beer> getUserWishlist(@RequestHeader HttpHeaders headers, @PathVariable int id) {
        try {
            User user = authenticationHelper.tryGetUser(headers);
            checkPermissionsForAccess(id, user);

            return new ArrayList<>(userService.getUserById(id).getWishList());
        } catch (EntityNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        } catch (AuthorizationException e) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, e.getMessage());
        }
    }

    @PutMapping("/{id}/wish-list/{beerId}")
    public void addBeerToUserWishlist(
            @RequestHeader HttpHeaders headers,
            @PathVariable int id,
            @PathVariable int beerId) {
        try {
            User user = authenticationHelper.tryGetUser(headers);
            checkPermissionsForAccess(id, user);

            userService.addBeerToWishList(id,beerId);
        } catch (EntityNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        } catch (AuthorizationException e) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, e.getMessage());
        }
    }

    @DeleteMapping("/{id}/wish-list/{beerId}")
    public void removeBeerFromUserWishlist(
            @RequestHeader HttpHeaders headers,
            @PathVariable int id,
            @PathVariable int beerId) {
        try {
            User user = authenticationHelper.tryGetUser(headers);
            checkPermissionsForAccess(id, user);

            userService.removeFromWishList(id,beerId);
        } catch (EntityNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        } catch (AuthorizationException e) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, e.getMessage());
        }
    }

    private void checkPermissionsForAccess(int id, User user) {
        if (user.isAdmin() || user.getId() == id) {
            return;
        }

        throw new AuthorizationException(AUTHORIZATION_ERROR);
    }
}
