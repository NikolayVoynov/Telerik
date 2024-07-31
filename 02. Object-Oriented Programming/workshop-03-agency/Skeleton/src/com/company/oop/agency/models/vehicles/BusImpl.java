package com.company.oop.agency.models.vehicles;

import com.company.oop.agency.models.vehicles.contracts.Bus;
import com.company.oop.agency.utils.ValidationHelper;

public class BusImpl extends VehicleImpl implements Bus {

    public static final int PASSENGER_MIN_VALUE = 10;
    public static final int PASSENGER_MAX_VALUE = 50;

    public static final String BUS_PASSENGER_CAPACITY_ERROR =
            "A bus cannot have less than %d passengers or more than %d passengers.";

    private VehicleType vehicleType;

    public BusImpl(int id, int passengerCapacity, double pricePerKilometer) {
        super(id, passengerCapacity, pricePerKilometer);
        this.vehicleType = VehicleType.LAND;
    }

    @Override
    public VehicleType getType() {
        return vehicleType;
    }

    @Override
    public String getAsString() {
        return String.format("Bus ----%n" + super.getAsString());
    }

    @Override
    protected void validatePassengerCapacity(int passengerCapacity) {
        ValidationHelper.validateValueInRange(passengerCapacity,
                PASSENGER_MIN_VALUE,
                PASSENGER_MAX_VALUE,
                String.format(BUS_PASSENGER_CAPACITY_ERROR, PASSENGER_MIN_VALUE, PASSENGER_MAX_VALUE));
    }

}