package com.company.oop.dealership.models;

import com.company.oop.dealership.models.contracts.Motorcycle;
import com.company.oop.dealership.models.enums.VehicleType;
import com.company.oop.dealership.utils.ValidationHelpers;


public class MotorcycleImpl extends BaseVehicle implements Motorcycle {

    private static final int MOTORCYCLE_WHEELS_COUNT = 2;
    private static final int CATEGORY_LEN_MIN = 3;
    private static final int CATEGORY_LEN_MAX = 10;
    private static final String CATEGORY_LEN_ERR = String.format(
            "Category must be between %d and %d characters long!",
            CATEGORY_LEN_MIN,
            CATEGORY_LEN_MAX);

    private String category;

    public MotorcycleImpl(String make, String model, double price, String category) {
        super(make, model, price, MOTORCYCLE_WHEELS_COUNT);
        setCategory(category);
    }

    @Override
    public VehicleType getType() {
        return VehicleType.MOTORCYCLE;
    }

    @Override
    public String getCategory() {
        return category;
    }

    @Override
    protected String printAdditionalInfoAsPerVehicleType() {
        return String.format("Category: %s", getCategory());
    }

    private void setCategory(String category) {
        ValidationHelpers.validateIntRange(category.length(), CATEGORY_LEN_MIN, CATEGORY_LEN_MAX, CATEGORY_LEN_ERR);

        this.category = category;
    }
}