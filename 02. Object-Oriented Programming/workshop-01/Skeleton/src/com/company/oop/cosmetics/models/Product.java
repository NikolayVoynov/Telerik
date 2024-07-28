package com.company.oop.cosmetics.models;

import com.company.oop.cosmetics.utils.ValidationHelpers;
import java.util.Objects;

public class Product {

    public static final int NAME_MIN_LENGTH = 3;
    public static final int NAME_MAX_LENGTH = 10;
    public static final int BRAND_MIN_LENGTH = 2;
    public static final int BRAND_MAX_LENGTH = 10;
    public static final int MIN_PRICE = 0;
    public static final String PRICE_ERROR = "Price should be non negative.";
    public static final String NAME_LENGTH_ERROR = "Name should be between 3 and 10 symbols.";
    public static final String BRAND_LENGTH_ERROR = "Brand should be between 2 and 10 symbols.";

    private String name;
    private String brand;
    private double price;
    private final GenderType gender;

    public Product(String name, String brand, double price, GenderType gender) {
        setName(name);
        setBrand(brand);
        setPrice(price);
        this.gender = gender;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public String getBrand() {
        return brand;
    }

    public GenderType getGender() {
       return gender;
    }

    public String print() {
        StringBuilder result = new StringBuilder();
        result.append(String.format(" #%s %s%n", name, brand))
                .append(String.format(" #Price: $%.2f%n", price))
                .append(String.format(" #Gender: %s", gender));

        return result.toString();
    }

    @Override
    public boolean equals(Object p) {
        if (this == p) return true;
        if (p == null || getClass() != p.getClass()) return false;
        Product product = (Product) p;
        return Double.compare(this.getPrice(), product.getPrice()) == 0 &&
                Objects.equals(this.getName(), product.getName()) &&
                Objects.equals(this.getBrand(), product.getBrand()) &&
                this.getGender() == product.getGender();
    }

    private void setName(String name) {
        ValidationHelpers.validateStringLength(
                name,
                NAME_MIN_LENGTH, NAME_MAX_LENGTH,
                NAME_LENGTH_ERROR);

        this.name = name;
    }

    private void setBrand(String brand) {
        ValidationHelpers.validateStringLength(
                brand,
                BRAND_MIN_LENGTH,
                BRAND_MAX_LENGTH,
                BRAND_LENGTH_ERROR);

        this.brand = brand;
    }

    private void setPrice(double price) {
        if (price < MIN_PRICE) {
            throw new IllegalArgumentException(PRICE_ERROR);
        }
        this.price = price;
    }
}
