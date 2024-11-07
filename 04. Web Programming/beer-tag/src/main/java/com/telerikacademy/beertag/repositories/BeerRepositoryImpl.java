package com.telerikacademy.beertag.repositories;

import com.telerikacademy.beertag.exceptions.EntityNotFoundException;
import com.telerikacademy.beertag.models.Beer;
import com.telerikacademy.beertag.models.FilterOptions;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class BeerRepositoryImpl implements BeerRepository {

    private final SessionFactory sessionFactory;

    @Autowired
    public BeerRepositoryImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public List<Beer> getAllBeers(FilterOptions filterOptions) {
        try (Session session = sessionFactory.openSession()) {

            List<String> filters = new ArrayList<>();
            Map<String, Object> params = new HashMap<>();

            filterOptions.getName().ifPresent(value -> {
                filters.add("name like :name");
                params.put("name", String.format("%%%s%%", value));
            });

            filterOptions.getMinAbv().ifPresent(value -> {
                filters.add("abv >= :minAbv");
                params.put("minAbv", value);
            });

            filterOptions.getMaxAbv().ifPresent(value -> {
                filters.add("abv <= :maxAbv");
                params.put("maxAbv", value);
            });

            filterOptions.getStyleId().ifPresent(value -> {
                filters.add("style.id = :styleId");
                params.put("styleId", value);
            });

            StringBuilder resultQuery = new StringBuilder();
            resultQuery.append("from Beer");
            if (!filters.isEmpty()) {
                resultQuery.append(" where ")
                        .append(String.join(" and ", filters));
            }

            resultQuery.append(generateOrderByQuery(filterOptions));

            Query<Beer> query = session.createQuery(resultQuery.toString(), Beer.class);
            query.setProperties(params);

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
    public void createNewBeer(Beer beer) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.persist(beer);
            session.getTransaction().commit();
        }
    }

    @Override
    public void updateBeer(Beer beer) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.merge(beer);
            session.getTransaction().commit();
        }
    }

    @Override
    public void deleteBeer(int id) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.remove(getBeerById(id));
            session.getTransaction().commit();
        }
    }

    private String generateOrderByQuery(FilterOptions filterOptions) {
        if (filterOptions.getSortBy().isEmpty()) {
            return "";
        }

        String orderByQuery = "";

        switch (filterOptions.getSortBy().get()) {
            case "name":
                orderByQuery = "name";
                break;
            case "abv":
                orderByQuery = "abv";
                break;
            case "style":
                orderByQuery = "style.name";
                break;
            default:
                return "";
        }

        orderByQuery = String.format(" order by %s", orderByQuery);

        if (filterOptions.getSortOrderType().isPresent() &&
                filterOptions.getSortOrderType().get().equalsIgnoreCase("desc")) {
            orderByQuery = String.format("%s desc", orderByQuery);
        }

        return orderByQuery;
    }
}
