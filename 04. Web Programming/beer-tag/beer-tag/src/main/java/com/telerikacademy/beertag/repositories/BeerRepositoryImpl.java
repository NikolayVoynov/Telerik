package com.telerikacademy.beertag.repositories;

import com.telerikacademy.beertag.exceptions.EntityNotFoundException;
import com.telerikacademy.beertag.models.Beer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.*;
import java.util.stream.Collectors;

@Repository
public class BeerRepositoryImpl implements BeerRepository {

    private static int counter = 4;

    private List<Beer> beers;
    private StyleRepository styleRepository;

    @Autowired
    public BeerRepositoryImpl(StyleRepository styleRepository) {
        this.styleRepository = styleRepository;

        this.beers = new ArrayList<>();

        beers.add(new Beer(1, "Zagorka", 4.5, styleRepository.getStyleById(1)));
        beers.add(new Beer(2, "Tuborg", 4.2, styleRepository.getStyleById(2)));
        beers.add(new Beer(3, "Bernard", 5.3, styleRepository.getStyleById(1)));
        beers.add(new Beer(4, "Heineken", 4.8, styleRepository.getStyleById(2)));
    }

    @Override
    public List<Beer> getAllBeers(String name, Double minAbv, Double maxAbv, Integer styleId, String sortBy, String sortOrderType) {
        List<Beer> resultBeers = new ArrayList<>(beers);

        resultBeers = filterByName(resultBeers, name);
        resultBeers = filterByAbv(resultBeers, minAbv, maxAbv);
        resultBeers = filterByStyle(resultBeers, styleId);
        resultBeers = sortBy(resultBeers, sortBy);
        resultBeers = sortOrderType(resultBeers, sortOrderType);

        return resultBeers;
    }


    @Override
    public Beer getBeerById(int id) {
        return getByIdHelper(id);
    }

    @Override
    public void createNewBeer(Beer beer) {
        beer.setId(++counter);
        beers.add(beer);
    }

    @Override
    public void updateBeer(Beer beer) {
        Beer beerToUpdate = getByIdHelper(beer.getId());

        beerToUpdate.setName(beer.getName());
        beerToUpdate.setAbv(beer.getAbv());
    }

    @Override
    public void deleteBeer(int id) {
        Beer beerToDelete = getByIdHelper(id);

        beers.remove(beerToDelete);
    }

    @Override
    public Beer getBeerByName(String name) {
        return beers
                .stream()
                .filter(beer -> beer.getName().equals(name))
                .findFirst()
                .orElseThrow(() -> new EntityNotFoundException("Beer", "name", name));
    }

    private Beer getByIdHelper(int id) {
        return beers.stream()
                .filter(beer -> beer.getId() == id)
                .findFirst()
                .orElseThrow(() -> new EntityNotFoundException("Beer", id));
    }

    private static List<Beer> filterByName(List<Beer> resultBeers, String name) {
        if (name != null && !name.isEmpty()) {
            resultBeers = resultBeers
                    .stream()
                    .filter(beer -> checkContainsIgnoredCase(beer.getName(), name))
                    .collect(Collectors.toList());
        }

        return resultBeers;
    }

    private static List<Beer> filterByAbv(List<Beer> resultBeers, Double minAbv, Double maxAbv) {
        if (maxAbv != null) {
            resultBeers = resultBeers
                    .stream()
                    .filter(beer -> beer.getAbv() <= maxAbv)
                    .collect(Collectors.toList());
        }

        if (minAbv != null) {
            resultBeers = resultBeers
                    .stream()
                    .filter(beer -> beer.getAbv() >= minAbv)
                    .collect(Collectors.toList());
        }

        return resultBeers;
    }

    private static List<Beer> filterByStyle(List<Beer> resultBeers, Integer styleId) {
        if (styleId != null) {
            resultBeers = resultBeers
                    .stream()
                    .filter(beer -> beer.getStyle().getId() == styleId)
                    .collect(Collectors.toList());
        }

        return resultBeers;
    }

    private static List<Beer> sortBy(List<Beer> resultBeers, String sortBy) {
        if (sortBy != null && !sortBy.isEmpty()) {
            switch (sortBy.toLowerCase()) {
                case "name":
                    resultBeers.sort(Comparator.comparing(Beer::getName));
                    break;
                case "abv":
                    resultBeers.sort(Comparator.comparing(Beer::getAbv));
                    break;
                case "style":
                    resultBeers.sort(Comparator.comparing(beer -> beer.getStyle().getName()));
                    break;
            }
        }

        return resultBeers;
    }

    private List<Beer> sortOrderType(List<Beer> resultBeers, String sortOrderType) {
        if (sortOrderType != null && !sortOrderType.isEmpty()) {
            if (sortOrderType.equalsIgnoreCase("desc")) {
                Collections.reverse(resultBeers);
            }
        }

        return resultBeers;
    }

    private static boolean checkContainsIgnoredCase(String first, String second) {
        return first.toLowerCase().contains(second.toLowerCase());
    }
}
