package com.telerikacademy.beer.models;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

public class BeerDto {

    @NotNull(message = "Name cannot be empty")
    @Size(min = 2, max = 20, message = "Name should be between 2 and 20")
    private String name;


    @Positive(message = "ABV should be positive")
    private double abv;

    @Positive(message = "StyleId should be positive")
    private int styleId;

    public BeerDto() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getAbv() {
        return abv;
    }

    public void setAbv(double abv) {
        this.abv = abv;
    }


    public int getStyleId() {
        return styleId;
    }

    public void setStyleId(int styleId) {
        this.styleId = styleId;
    }
}
