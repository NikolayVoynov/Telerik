package com.telerikacademy.beertag.services;

import com.telerikacademy.beertag.models.Style;

import java.util.List;

public interface StyleService {
    List<Style> getAllStyles();

    Style getStyleById(int id);
}
