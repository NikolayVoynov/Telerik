package com.telerikacademy.beertag.repositories;

import com.telerikacademy.beertag.models.Style;

import java.util.List;

public interface StyleRepository {
    List<Style> getAllStyles();

    Style getStyleById(int id);
}
