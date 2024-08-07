package com.company.oop.dealership.models;

import com.company.oop.dealership.models.contracts.Truck;
import com.company.oop.dealership.models.enums.VehicleType;
import com.company.oop.dealership.utils.ValidationHelpers;

public class TruckImpl extends BaseVehicle implements Truck {

    private static final int TRUCK_WHEELS_COUNT = 8;
    private static final int WEIGHT_CAP_MIN = 1;
    private static final int WEIGHT_CAP_MAX = 100;
    private static final String WEIGHT_CAP_ERR = String.format(
            "Weight capacity must be between %d and %d!",
            WEIGHT_CAP_MIN,
            WEIGHT_CAP_MAX);

    private int weightCapacity;

    public TruckImpl(String make, String model, double price, int weightCapacity) {
        super(make, model, price, TRUCK_WHEELS_COUNT);
        setWeightCapacity(weightCapacity);
    }

    @Override
    public VehicleType getType() {
        return VehicleType.TRUCK;
    }

    @Override
    public int getWeightCapacity() {
        return weightCapacity;
    }

    @Override
    protected String printAdditionalInfoAsPerVehicleType() {
        return String.format("Weight Capacity: %dt", getWeightCapacity());
    }

    private void setWeightCapacity(int weightCapacity) {
        ValidationHelpers.validateIntRange(weightCapacity, WEIGHT_CAP_MIN, WEIGHT_CAP_MAX, WEIGHT_CAP_ERR);

        this.weightCapacity = weightCapacity;
    }
}