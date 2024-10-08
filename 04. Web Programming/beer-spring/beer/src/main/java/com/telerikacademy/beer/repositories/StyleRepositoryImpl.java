package com.telerikacademy.beer.repositories;

import com.telerikacademy.beer.exceptions.EntityNotFoundException;
import com.telerikacademy.beer.models.Style;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

@Repository
public class StyleRepositoryImpl implements StyleRepository {

    private final SessionFactory sessionFactory;

    public StyleRepositoryImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public Style getById(int id) {
        try (Session session = sessionFactory.openSession()) {
            Style style = session.get(Style.class, id);

            if (style == null) {
                throw new EntityNotFoundException("Style", id);
            }

            return style;
        }
    }
}
