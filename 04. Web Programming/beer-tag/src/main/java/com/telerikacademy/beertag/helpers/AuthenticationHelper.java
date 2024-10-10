package com.telerikacademy.beertag.helpers;

import com.telerikacademy.beertag.exceptions.AuthorizationException;
import com.telerikacademy.beertag.exceptions.EntityNotFoundException;
import com.telerikacademy.beertag.models.User;
import com.telerikacademy.beertag.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;

@Component
public class AuthenticationHelper {
    public static final String AUTHORIZATION_HEADER = "Authorization";
    public static final String INVALID_AUTHENTICATION_ERROR = "Invalid authentication.";
    private final UserService userService;

    @Autowired
    public AuthenticationHelper(UserService userService) {
        this.userService = userService;
    }

    public User tryGetUser(HttpHeaders headers) {
        if (!headers.containsKey(AUTHORIZATION_HEADER)) {
            throw new AuthorizationException(INVALID_AUTHENTICATION_ERROR);
        }

        try {

            String usernameAndPassword = headers.getFirst(AUTHORIZATION_HEADER);
            String[] tokens = usernameAndPassword.split("_");
            String username = tokens[0];
            String passwordHeader = tokens[1];

            User user = userService.getUserByName(username);
            String passwordUser = user.getPassword();

            if (!passwordUser.equals(passwordHeader)) {
                throw new AuthorizationException(INVALID_AUTHENTICATION_ERROR);
            }

            return user;

        } catch (EntityNotFoundException e) {
            throw new AuthorizationException(INVALID_AUTHENTICATION_ERROR);
        }
    }
}
