package com.company.oop.agency.models.vehicles;

import com.company.oop.agency.models.vehicles.contracts.Train;
import com.company.oop.agency.utils.ValidationHelper;

public class TrainImpl extends VehicleImpl implements Train {

    public static final int PASSENGER_MIN_VALUE = 30;
    public static final int PASSENGER_MAX_VALUE = 150;
    public static final int CARTS_MIN_VALUE = 1;
    public static final int CARTS_MAX_VALUE = 15;

    public static final String TRAIN_PASSENGER_CAPACITY_ERROR =
            "A train cannot have less than %d passengers or more than %d passengers.";
    public static final String CARTS_VALUE_ERROR =
            "A train cannot have less than %d cart or more than %d carts.";

    private int carts;
    private VehicleType vehicleType;

    public TrainImpl(int id, int passengerCapacity, double pricePerKilometer, int carts) {
        super(id, passengerCapacity, pricePerKilometer);
        setCarts(carts);
        this.vehicleType = VehicleType.LAND;
    }

    @Override
    public VehicleType getType() {
        return vehicleType;
    }

    @Override
    public int getCarts() {
        return carts;
    }

    @Override
    public String getAsString() {
        return String.format("Train ----%n" +
                super.getAsString() +
                "Carts amount: %d%n", carts);
    }

    @Override
    protected void validatePassengerCapacity(int passengerCapacity) {
        ValidationHelper.validateValueInRange(passengerCapacity,
                PASSENGER_MIN_VALUE,
                PASSENGER_MAX_VALUE,
                String.format(TRAIN_PASSENGER_CAPACITY_ERROR, PASSENGER_MIN_VALUE, PASSENGER_MAX_VALUE));
    }

    private void setCarts(int carts) {
        ValidationHelper.validateValueInRange(carts,
                CARTS_MIN_VALUE,
                CARTS_MAX_VALUE,
                String.format(CARTS_VALUE_ERROR, CARTS_MIN_VALUE, CARTS_MAX_VALUE));

        this.carts = carts;
    }

}