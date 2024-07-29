package com.company.oop.cosmetics.models;

import com.company.oop.cosmetics.models.contracts.Product;
import com.company.oop.cosmetics.models.enums.GenderType;

public abstract class BaseProduct implements Product {

    public static final int NAME_MIN_LENGTH = 3;
    public static final int NAME_MAX_LENGTH = 10;
    public static final int BRAND_NAME_MIN_LENGTH = 2;
    public static final int BRAND_NAME_MAX_LENGTH = 10;
    public static final double MIN_PRICE = 0;
    public static final String PRICE_SHOULD_BE_NON_NEGATIVE = "Price should be non negative.";

    private String name;
    private String brandName;
    private double price;
    private GenderType genderType;

    public BaseProduct(String name, String brandName, double price, GenderType genderType) {
        setName(name);
        setBrandName(brandName);
        setPrice(price);
        this.genderType = genderType;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getBrandName() {
        return brandName;
    }

    @Override
    public double getPrice() {
        return price;
    }

    @Override
    public GenderType getGenderType() {
        return genderType;
    }

    @Override
    public String print() {
        StringBuilder result = new StringBuilder();

        result.append(String.format("#%s %s%n", name, brandName));
        result.append(String.format(" #Price: $%.2f%n", price));
        result.append(String.format(" #Gender: %s%n", genderType));

        return result.toString();
    }

    protected abstract void validateNameLength(String name);

    protected abstract void validateBrandLength(String brandName);

    protected abstract void validatePrice(double price);

    private void setName(String name) {
        validateNameLength(name);

        this.name = name;
    }

    private void setBrandName(String brandName) {
        validateBrandLength(brandName);

        this.brandName = brandName;
    }

    private void setPrice(double price) {
        validatePrice(price);

        this.price = price;
    }
}
