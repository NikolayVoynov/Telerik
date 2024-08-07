package com.company.oop.dealership.models;

import com.company.oop.dealership.models.contracts.Car;
import com.company.oop.dealership.models.enums.VehicleType;
import com.company.oop.dealership.utils.ValidationHelpers;

public class CarImpl extends BaseVehicle implements Car {

    private static final int CAR_WHEELS_COUNT = 4;
    private static final int CAR_SEATS_MIN = 1;
    private static final int CAR_SEATS_MAX = 10;
    private static final String CAR_SEATS_ERR = String.format(
            "Seats must be between %d and %d!",
            CAR_SEATS_MIN,
            CAR_SEATS_MAX);

    private int seats;

    public CarImpl(String make, String model, double price, int seats) {
        super(make, model, price, CAR_WHEELS_COUNT);
        setSeats(seats);
    }

    @Override
    public VehicleType getType() {
        return VehicleType.CAR;
    }

    @Override
    public int getSeats() {
        return seats;
    }

    @Override
    protected String printAdditionalInfoAsPerVehicleType() {
        return String.format("Seats: %d", getSeats());
    }

    private void setSeats(int seats) {
        ValidationHelpers.validateIntRange(seats, CAR_SEATS_MIN, CAR_SEATS_MAX, CAR_SEATS_ERR);

        this.seats = seats;
    }

}