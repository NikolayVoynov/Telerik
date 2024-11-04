package com.telerikacademy.beertag.helpers;

import com.telerikacademy.beertag.exceptions.AuthenticationFailureException;
import com.telerikacademy.beertag.exceptions.AuthorizationException;
import com.telerikacademy.beertag.exceptions.EntityNotFoundException;
import com.telerikacademy.beertag.models.User;
import com.telerikacademy.beertag.services.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;

@Component
public class AuthenticationHelper {
    public static final String AUTHORIZATION_HEADER = "Authorization";
    public static final String INVALID_AUTHENTICATION_ERROR = "Invalid authentication.";
    public static final String AUTHENTICATION_FAILURE_MESSAGE = "Wrong username or password!";
    public static final String NO_USER_LOGGED_IN_ERROR_MESSAGE = "No user logged in.";

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

    public User tryGetUser(HttpSession session) {
        String currentUser = session.getAttribute("currentUser").toString();

        if (currentUser == null) {
            throw new AuthenticationFailureException(NO_USER_LOGGED_IN_ERROR_MESSAGE);
        }

        return userService.getUserByName(currentUser);
    }

    public User verifyAuthentication(String username, String password) {
        try {
            User user = userService.getUserByName(username);

            if (!user.getPassword().equals(password)) {
                throw new AuthenticationFailureException(AUTHENTICATION_FAILURE_MESSAGE);
            }

            return user;
        } catch (EntityNotFoundException e){
            throw new AuthenticationFailureException(AUTHENTICATION_FAILURE_MESSAGE);
        }
    }
}
