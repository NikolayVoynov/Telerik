package com.company.oop.cosmetics.models;

import com.company.oop.cosmetics.models.contracts.Toothpaste;
import com.company.oop.cosmetics.models.enums.GenderType;
import com.company.oop.cosmetics.utils.ValidationHelpers;

import java.util.ArrayList;
import java.util.List;

public class ToothpasteImpl extends BaseProduct implements Toothpaste {

    List<String> ingredients;

    public ToothpasteImpl(String name, String brandName, double price, GenderType genderType, List<String> ingredients) {
        super(name, brandName, price, genderType);
        this.ingredients = ingredients;
    }

    @Override
    public List<String> getIngredients() {
        return new ArrayList<>(ingredients);
    }

    @Override
    public String print() {
        StringBuilder result = new StringBuilder();

        result.append(super.print());
        result.append(String.format(" #Ingredients: %s%n", ingredients));

        return result.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ToothpasteImpl toothpaste = (ToothpasteImpl) o;
        return getName().equals(toothpaste.getName()) &&
                getBrandName().equals(toothpaste.getBrandName()) &&
                getPrice() == toothpaste.getPrice() &&
                this.getGenderType().equals(toothpaste.getGenderType()) &&
                getIngredients().equals(toothpaste.getIngredients());
    }

    @Override
    protected void validateNameLength(String name) {
        ValidationHelpers.validateStringLength(name, NAME_MIN_LENGTH, NAME_MAX_LENGTH, "Name");
    }

    @Override
    protected void validateBrandLength(String brandName) {
        ValidationHelpers.validateStringLength(brandName, BRAND_NAME_MIN_LENGTH, BRAND_NAME_MAX_LENGTH, "Brand");
    }

    @Override
    protected void validatePrice(double price) {
        if (price < MIN_PRICE) {
            throw new IllegalArgumentException(PRICE_SHOULD_BE_NON_NEGATIVE);
        }
    }
}
