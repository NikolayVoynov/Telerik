package com.telerikacademy.beertag.models;

import java.util.Optional;

public class FilterOptions {

    private Optional<String> name;
    private Optional<Double> minAbv;
    private Optional<Double> maxAbv;
    private Optional<Integer> styleId;
    private Optional<String> sortBy;
    private Optional<String> sortOrderType;

    public FilterOptions() {
        this.name = Optional.empty();
        this.minAbv = Optional.empty();
        this.maxAbv = Optional.empty();
        this.styleId = Optional.empty();
        this.sortBy = Optional.empty();
        this.sortOrderType = Optional.empty();
    }

    public FilterOptions(
            String name,
            Double minAbv,
            Double maxAbv,
            Integer styleId,
            String sortBy,
            String sortOrderType) {
        this.name = Optional.ofNullable(name);
        this.minAbv = Optional.ofNullable(minAbv);
        this.maxAbv = Optional.ofNullable(maxAbv);
        this.styleId = Optional.ofNullable(styleId);
        this.sortBy = Optional.ofNullable(sortBy);
        this.sortOrderType = Optional.ofNullable(sortOrderType);
    }

    public Optional<String> getName() {
        return name;
    }

    public Optional<Double> getMinAbv() {
        return minAbv;
    }

    public Optional<Double> getMaxAbv() {
        return maxAbv;
    }

    public Optional<Integer> getStyleId() {
        return styleId;
    }

    public Optional<String> getSortBy() {
        return sortBy;
    }

    public Optional<String> getSortOrderType() {
        return sortOrderType;
    }
}
