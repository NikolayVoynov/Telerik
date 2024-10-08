package com.telerikacademy.beer.repositories;

import com.telerikacademy.beer.exceptions.EntityNotFoundException;
import com.telerikacademy.beer.models.Beer;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class BeerRepositoryImpl implements BeerRepository {
    private final SessionFactory sessionFactory;

    @Autowired
    public BeerRepositoryImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public List<Beer> getAllBeers() {
        try (Session session = sessionFactory.openSession()) {
            Query<Beer> query = session.createQuery("from Beer", Beer.class);
            return query.list();
        }
    }

    @Override
    public Beer getBeerById(int id) {
        try (Session session = sessionFactory.openSession()) {
            Beer beer = session.get(Beer.class, id);
            if (beer == null) {
                throw new EntityNotFoundException("Beer", id);
            }

            return beer;
        }
    }

    @Override
    public Beer getBeerByName(String name) {
        try (Session session = sessionFactory.openSession()) {
            Query<Beer> query = session.createQuery("from Beer where name = :name", Beer.class);
            query.setParameter("name", name);

            List<Beer> beers = query.list();

            if (beers.isEmpty()) {
                throw new EntityNotFoundException("Beer", "name", name);
            }

            return beers.get(0);
        }
    }

    @Override
    public void createBeer(Beer beer) {
        try (Session session = sessionFactory.openSession()) {
            session.save(beer);
        }
    }

    @Override
    public void updateBeer(Beer beer) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.update(beer);
            session.getTransaction().commit();
        }
    }

    @Override
    public void delete(int id) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();

            Beer beer = session.get(Beer.class, id);

            if (beer == null) {
                throw new EntityNotFoundException("Beer", id);
            }

            session.delete(beer);
            session.getTransaction().commit();
        }
    }


}
