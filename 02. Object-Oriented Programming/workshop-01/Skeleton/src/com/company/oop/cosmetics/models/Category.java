package com.company.oop.cosmetics.models;

import com.company.oop.cosmetics.utils.ValidationHelpers;

import java.util.ArrayList;

public class Category {
    public static final int NAME_MIN_LENGTH = 2;
    public static final int NAME_MAX_LENGTH = 15;
    public static final String NAME_LENGTH_ERROR = "Name should be between 2 and 15 symbols.";
    public static final String PRODUCT_NOT_FOUND_IN_CATEGORY = "Product not found in category.";

    private String name;
    private ArrayList<Product> products;

    public Category(String name) {
        setName(name);
        products = new ArrayList<>();
    }

    public String getName() {
        return name;
    }


    public ArrayList<Product> getProducts() {
        return new ArrayList<>(products);
    }

    public void addProduct(Product product) {
        products.add(product);
    }

    public void removeProduct(Product product) {
        if (!products.contains(product)) {
            throw new IllegalArgumentException(PRODUCT_NOT_FOUND_IN_CATEGORY);
        }

        products.remove(product);
    }

    public String print() {
        StringBuilder result = new StringBuilder();

        result.append(String.format("#Category: %s%n", name));

        if (products.isEmpty()) {
            result.append(" #No product in this category");
        } else {
            for (Product product : products) {
                result.append(product.print());
                result.append(System.lineSeparator());
                result.append(" ===");
            }
        }

        return result.toString();
    }

    private void setName(String name) {
        ValidationHelpers.validateStringLength(
                name,
                NAME_MIN_LENGTH,
                NAME_MAX_LENGTH,
                NAME_LENGTH_ERROR);

        this.name = name;
    }
}
