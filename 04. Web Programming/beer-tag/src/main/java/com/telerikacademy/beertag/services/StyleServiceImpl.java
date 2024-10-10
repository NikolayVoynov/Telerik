package com.telerikacademy.beertag.services;

import com.telerikacademy.beertag.models.Style;
import com.telerikacademy.beertag.repositories.StyleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StyleServiceImpl implements StyleService {

    private StyleRepository styleRepository;

    @Autowired
    public StyleServiceImpl(StyleRepository styleRepository) {
        this.styleRepository = styleRepository;
    }


    @Override
    public List<Style> getAllStyles() {
        return styleRepository.getAllStyles();
    }


    @Override
    public Style getStyleById(int id) {
        return styleRepository.getStyleById(id);
    }

}
