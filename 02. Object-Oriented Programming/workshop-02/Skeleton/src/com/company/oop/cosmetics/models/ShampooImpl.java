package com.company.oop.cosmetics.models;

import com.company.oop.cosmetics.models.contracts.Shampoo;
import com.company.oop.cosmetics.models.enums.GenderType;
import com.company.oop.cosmetics.models.enums.UsageType;
import com.company.oop.cosmetics.utils.ValidationHelpers;

public class ShampooImpl extends BaseProduct implements Shampoo {

    public static final String MILLILITERS_SHOULD_BE_NON_NEGATIVE = "Milliliters should be non negative.";
    public static final int MIN_MILLILITERS = 0;

    private int milliliters;
    private UsageType usageType;

    public ShampooImpl(String name, String brand, double price,
                       GenderType genderType, int milliliters, UsageType usageType) {
        super(name, brand, price, genderType);
        setMilliliters(milliliters);
        this.usageType = usageType;
    }

    @Override
    public int getMillilitres() {
        return milliliters;
    }

    @Override
    public UsageType getUsageType() {
        return usageType;
    }

    @Override
    public String print() {
        StringBuilder result = new StringBuilder();

        result.append(super.print());
        result.append(String.format(" #Milliliters: %d%n", milliliters));
        result.append(String.format(" #Usage: %s%n", usageType));

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
        if (price < MIN_PRICE) {
            throw new IllegalArgumentException(PRICE_SHOULD_BE_NON_NEGATIVE);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ShampooImpl shampoo = (ShampooImpl) o;
        return getName().equals(shampoo.getName()) &&
                getBrandName().equals(shampoo.getBrandName()) &&
                getPrice() == shampoo.getPrice() &&
                getGenderType().equals(shampoo.getGenderType()) &&
                getMillilitres() == shampoo.getMillilitres() &&
                getUsageType().equals(shampoo.getUsageType());
    }

    private void setMilliliters(int milliliters){
        if (milliliters < MIN_MILLILITERS){
            throw new IllegalArgumentException(MILLILITERS_SHOULD_BE_NON_NEGATIVE);
        }

        this.milliliters = milliliters;
    }
}
