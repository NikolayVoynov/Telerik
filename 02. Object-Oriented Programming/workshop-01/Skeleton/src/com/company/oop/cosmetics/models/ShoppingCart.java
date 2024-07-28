package com.company.oop.cosmetics.models;

import java.util.ArrayList;

public class ShoppingCart {

    public static final String NO_PRODUCT_IN_SHOPPING_CART_ERROR =
            "Shopping cart does not contain product with name %s!";
    private ArrayList<Product> products;

    public ShoppingCart() {
        products = new ArrayList<>();
    }

    public ArrayList<Product> getProducts() {
        return new ArrayList<>(products);
    }

    public void addProduct(Product product) {
       products.add(product);
    }

    public void removeProduct(Product product) {
        if (!containsProduct(product)){
            throw new IllegalArgumentException(String.format(NO_PRODUCT_IN_SHOPPING_CART_ERROR,
                    product.getName()));
        }

        products.remove(product);
    }
    
    public boolean containsProduct(Product product) {
        return products.contains(product);
    }
    
    public double totalPrice() {
        double totalPrice = 0;
        for (Product product : products) {
            totalPrice += product.getPrice();
        }

        return totalPrice;
    }
    
}
