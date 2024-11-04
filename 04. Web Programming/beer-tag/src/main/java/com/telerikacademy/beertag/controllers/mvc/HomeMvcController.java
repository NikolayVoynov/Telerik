package com.telerikacademy.beertag.controllers.mvc;

import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class HomeMvcController {

    @GetMapping
    public String showHomePage(){
        return "HomeView";
    }

    @GetMapping("/about")
    public String showAboutPage(){
        return "About";
    }

    @ModelAttribute("isAuthenticated")
    public  boolean populateIsAuthenticated(HttpSession session){
        return session.getAttribute("currentUser") != null;
    }
}
