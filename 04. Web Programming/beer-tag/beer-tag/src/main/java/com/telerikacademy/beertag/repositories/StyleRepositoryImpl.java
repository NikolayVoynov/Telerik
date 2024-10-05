package com.telerikacademy.beertag.repositories;

import com.telerikacademy.beertag.exceptions.EntityNotFoundException;
import com.telerikacademy.beertag.models.Style;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class StyleRepositoryImpl implements StyleRepository {

    private final List<Style> styles;

    public StyleRepositoryImpl() {
        this.styles = new ArrayList<>();
        styles.add(new Style(1, "Light"));
        styles.add(new Style(2, "Dark"));
    }

    @Override
    public List<Style> getAllStyles() {
        return styles;
    }

    @Override
    public Style getStyleById(int id) {
        return styles
                .stream()
                .filter(style -> style.getId() == id)
                .findFirst()
                .orElseThrow(() -> new EntityNotFoundException("Style", id));
    }
}
