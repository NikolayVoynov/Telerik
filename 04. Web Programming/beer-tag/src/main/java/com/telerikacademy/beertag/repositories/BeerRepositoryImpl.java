package com.telerikacademy.beertag.repositories;

import com.telerikacademy.beertag.exceptions.EntityNotFoundException;
import com.telerikacademy.beertag.models.Beer;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.*;
import java.util.stream.Collectors;

@Repository
public class BeerRepositoryImpl implements BeerRepository {

    private final SessionFactory sessionFactory;

    @Autowired
    public BeerRepositoryImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public List<Beer> getAllBeers(String name, Double minAbv, Double maxAbv, Integer styleId, String sortBy, String sortOrderType) {
//        List<Beer> resultBeers = new ArrayList<>();
//
//        resultBeers = filterByName(resultBeers, name);
//        resultBeers = filterByAbv(resultBeers, minAbv, maxAbv);
//        resultBeers = filterByStyle(resultBeers, styleId);
//        resultBeers = sortBy(resultBeers, sortBy);
//        resultBeers = sortOrderType(resultBeers, sortOrderType);
//
//        return resultBeers;
        return null;
    }


    @Override
    public Beer getBeerById(int id) {
        try (Session session = sessionFactory.openSession()) {
            Query<Beer> query = session.createQuery("from Beer where id = :id", Beer.class);
            query.setParameter("id", id);

            List<Beer> beers = query.list();

            if (beers.isEmpty()) {
                throw new EntityNotFoundException("Beer", id);
            }

            return beers.get(0);
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
        try(Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.persist(beer);
            session.getTransaction().commit();
        }
    }

    @Override
    public void updateBeer(Beer beer) {
        try(Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.merge(beer);
            session.getTransaction().commit();
        }
    }

    @Override
    public void deleteBeer(int id) {
        try(Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.remove(getBeerById(id));
            session.getTransaction().commit();
        }
    }

//    private static List<Beer> filterByName(List<Beer> resultBeers, String name) {
//        if (name != null && !name.isEmpty()) {
//            resultBeers = resultBeers
//                    .stream()
//                    .filter(beer -> checkContainsIgnoredCase(beer.getName(), name))
//                    .collect(Collectors.toList());
//        }
//
//        return resultBeers;
//    }

//    private static List<Beer> filterByAbv(List<Beer> resultBeers, Double minAbv, Double maxAbv) {
//        if (maxAbv != null) {
//            resultBeers = resultBeers
//                    .stream()
//                    .filter(beer -> beer.getAbv() <= maxAbv)
//                    .collect(Collectors.toList());
//        }
//
//        if (minAbv != null) {
//            resultBeers = resultBeers
//                    .stream()
//                    .filter(beer -> beer.getAbv() >= minAbv)
//                    .collect(Collectors.toList());
//        }
//
//        return resultBeers;
//    }

//    private static List<Beer> filterByStyle(List<Beer> resultBeers, Integer styleId) {
//        if (styleId != null) {
//            resultBeers = resultBeers
//                    .stream()
//                    .filter(beer -> beer.getStyle().getId() == styleId)
//                    .collect(Collectors.toList());
//        }
//
//        return resultBeers;
//    }

//    private static List<Beer> sortBy(List<Beer> resultBeers, String sortBy) {
//        if (sortBy != null && !sortBy.isEmpty()) {
//            switch (sortBy.toLowerCase()) {
//                case "name":
//                    resultBeers.sort(Comparator.comparing(Beer::getName));
//                    break;
//                case "abv":
//                    resultBeers.sort(Comparator.comparing(Beer::getAbv));
//                    break;
//                case "style":
//                    resultBeers.sort(Comparator.comparing(beer -> beer.getStyle().getName()));
//                    break;
//            }
//        }
//
//        return resultBeers;
//    }

//    private List<Beer> sortOrderType(List<Beer> resultBeers, String sortOrderType) {
//        if (sortOrderType != null && !sortOrderType.isEmpty()) {
//            if (sortOrderType.equalsIgnoreCase("desc")) {
//                Collections.reverse(resultBeers);
//            }
//        }
//
//        return resultBeers;
//    }

//    private static boolean checkContainsIgnoredCase(String first, String second) {
//        return first.toLowerCase().contains(second.toLowerCase());
//    }
}
