package com.telerikacademy.beertag.controllers.mvc;

import com.telerikacademy.beertag.exceptions.EntityDuplicateException;
import com.telerikacademy.beertag.exceptions.EntityNotFoundException;
import com.telerikacademy.beertag.helpers.UserMapper;
import com.telerikacademy.beertag.models.LoginDto;
import com.telerikacademy.beertag.models.RegisterDto;
import com.telerikacademy.beertag.models.User;
import com.telerikacademy.beertag.services.UserService;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/auth")
public class AuthenticationController {

    public static final String INCORRECT_PASSWORD_MESSAGE = "Incorrect password";
    public static final String PASSWORD_CONFIRMATION_ERROR_MESSAGE = "Password confirmation should match password!";

    private final UserService userService;
    private final UserMapper userMapper;

    @Autowired
    public AuthenticationController(UserService userService, UserMapper userMapper) {
        this.userService = userService;
        this.userMapper = userMapper;
    }

    @GetMapping("/login")
    public String showLoginPage(Model model) {
        model.addAttribute("login", new LoginDto());

        return "LoginView";
    }

    @PostMapping("/login")
    public String handleLogin(@Valid @ModelAttribute("login") LoginDto loginDto,
                              BindingResult bindingResult,
                              HttpSession session) {
        User user;
        try {
            user = userService.getUserByName(loginDto.getUsername());
            if (!user.getPassword().equals(loginDto.getPassword())) {
                bindingResult.rejectValue("password", "auth_error", INCORRECT_PASSWORD_MESSAGE);
                return "LoginView";
            }

        } catch (EntityNotFoundException e) {
            bindingResult.rejectValue("username", "auth_error", e.getMessage());
            return "LoginView";
        }

        session.setAttribute("currentUser", user.getUsername());
        return "redirect:/";
    }

    @GetMapping("/logout")
    public String handleLogout(HttpSession session) {
        session.removeAttribute("currentUser");
        return "redirect:/";
    }

    @GetMapping("/register")
    public String showRegisterPage(Model model) {
        model.addAttribute("register", new RegisterDto());

        return "RegisterView";
    }

    @PostMapping("/register")
    public String handleRegister(@Valid @ModelAttribute("register") RegisterDto registerDto,
                                 BindingResult bindingResult,
                                 HttpSession session) {

        if (bindingResult.hasErrors()) {
            return "RegisterView";
        }

        if (!registerDto.getPassword().equals(registerDto.getPasswordConfirmed())) {
            bindingResult.rejectValue("passwordConfirmed", "password_error", PASSWORD_CONFIRMATION_ERROR_MESSAGE);
            return "RegisterView";
        }

        try {
            User user = userMapper.dtoToUser(registerDto);
            userService.createUser(user);
            return "redirect:/auth/login";

        } catch (EntityDuplicateException e) {
            bindingResult.rejectValue("username", "auth_error", e.getMessage());
            return "RegisterView";
        }
    }
}
