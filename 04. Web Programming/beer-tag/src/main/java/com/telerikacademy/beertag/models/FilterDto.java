package com.telerikacademy.beertag.models;

import java.util.Optional;

public class FilterDto {

    private String name;
    private Double minAbv;
    private Double maxAbv;
    private Integer styleId;
    private String sortBy;
    private String sortOrderType;

    public FilterDto() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getMinAbv() {
        return minAbv;
    }

    public void setMinAbv(Double minAbv) {
        this.minAbv = minAbv;
    }

    public Double getMaxAbv() {
        return maxAbv;
    }

    public void setMaxAbv(Double maxAbv) {
        this.maxAbv = maxAbv;
    }

    public Integer getStyleId() {
        return styleId;
    }

    public void setStyleId(Integer styleId) {
        this.styleId = styleId;
    }

    public String getSortBy() {
        return sortBy;
    }

    public void setSortBy(String sortBy) {
        this.sortBy = sortBy;
    }

    public String getSortOrderType() {
        return sortOrderType;
    }

    public void setSortOrderType(String sortOrderType) {
        this.sortOrderType = sortOrderType;
    }
}
