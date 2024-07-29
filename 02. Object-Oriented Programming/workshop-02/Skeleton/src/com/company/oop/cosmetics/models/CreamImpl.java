package com.company.oop.cosmetics.models;

import com.company.oop.cosmetics.models.contracts.Cream;
import com.company.oop.cosmetics.models.enums.GenderType;
import com.company.oop.cosmetics.models.enums.ScentType;
import com.company.oop.cosmetics.utils.ValidationHelpers;

public class CreamImpl extends BaseProduct implements Cream {
    public static final int NAME_MIN_LENGTH = 3;
    public static final int NAME_MAX_LENGTH = 15;
    public static final int BRAND_NAME_MIN_LENGTH = 3;
    public static final int BRAND_NAME_MAX_LENGTH = 15;

    public static final double NULL_PRICE = 0;
    public static final String PRICE_SHOULD_BE_GREATER_THAN_ZERO = "Price should be non negative.";

    private ScentType scentType;

    public CreamImpl(String name, String brandName, double price, GenderType genderType, ScentType scentType) {
        super(name, brandName, price, genderType);
        this.scentType = scentType;
    }

    @Override
    public ScentType getScentType() {
        return scentType;
    }

    @Override
    public String print() {
        StringBuilder result = new StringBuilder();

        result.append(super.print());
        result.append(String.format(" #Scent: %s%n", scentType));

        return result.toString();
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
        if (price <= NULL_PRICE) {
            throw new IllegalArgumentException(PRICE_SHOULD_BE_GREATER_THAN_ZERO);
        }
    }
}
