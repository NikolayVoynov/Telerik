package com.telerikacademy.beertag.repositories;

import com.telerikacademy.beertag.exceptions.EntityNotFoundException;
import com.telerikacademy.beertag.models.Style;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class StyleRepositoryImpl implements StyleRepository {

    private final SessionFactory sessionFactory;

    @Autowired
    public StyleRepositoryImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public List<Style> getAllStyles() {
        try (Session session = sessionFactory.openSession()) {
            return session
                    .createQuery("from Style", Style.class)
                    .list();
        }
    }

    @Override
    public Style getStyleById(int id) {
        try (Session session = sessionFactory.openSession()) {
            Style style = session.get(Style.class, id);

            if (style == null) {
                throw new EntityNotFoundException("Style", id);
            }

            return style;
        }
    }
}
