package com.company.oop.agency.models.vehicles;

import com.company.oop.agency.models.vehicles.contracts.Vehicle;
import com.company.oop.agency.utils.ValidationHelper;

public abstract class VehicleImpl implements Vehicle {
    public static final int VEHICLE_PASSENGER_MIN_VALUE = 1;
    public static final int VEHICLE_PASSENGER_MAX_VALUE = 800;
    public static final double PRICE_MIN_VALUE = 0.1;
    public static final double PRICE_MAX_VALUE = 2.5;

    public static final String VEHICLE_PASSENGER_CAPACITY_ERROR =
            "A vehicle with less than %d passenger or more than %d passengers cannot exist!";
    public static final String PRICE_VALUE_ERROR =
            "A vehicle with a price per kilometer lower than $%.2f or higher than $%.2f cannot exist!";



    private int id;
    private int passengerCapacity;
    private double pricePerKilometer;


    public VehicleImpl(int id, int passengerCapacity, double pricePerKilometer) {
        this.id = id;
        setPassengerCapacity(passengerCapacity);
        setPricePerKilometer(pricePerKilometer);
    }

    @Override
    public int getId() {
        return id;
    }

    @Override
    public abstract VehicleType getType();

    @Override
    public int getPassengerCapacity() {
        return passengerCapacity;
    }

    @Override
    public double getPricePerKilometer() {
        return pricePerKilometer;
    }

    public String getAsString() {
        return String.format(
                "Passenger capacity: %d%n" +
                        "Price per kilometer: %.2f%n" +
                        "Vehicle type: %s%n", passengerCapacity, pricePerKilometer, getType());
    }

    protected void validatePassengerCapacity(int passengerCapacity) {
        ValidationHelper.validateValueInRange(passengerCapacity,
                VEHICLE_PASSENGER_MIN_VALUE,
                VEHICLE_PASSENGER_MAX_VALUE,
                String.format(VEHICLE_PASSENGER_CAPACITY_ERROR,
                        VEHICLE_PASSENGER_MIN_VALUE,
                        VEHICLE_PASSENGER_MAX_VALUE));
    }

    protected void validatePricePerKilometer(double pricePerKilometer){
        ValidationHelper.validateValueInRange(pricePerKilometer,
                PRICE_MIN_VALUE,
                PRICE_MAX_VALUE,
                String.format(PRICE_VALUE_ERROR,
                        PRICE_MIN_VALUE,
                        PRICE_MAX_VALUE));
    }

    private void setPassengerCapacity(int passengerCapacity) {
        validatePassengerCapacity(passengerCapacity);

        this.passengerCapacity = passengerCapacity;
    }

    private void setPricePerKilometer(double pricePerKilometer) {
        validatePricePerKilometer(pricePerKilometer);

        this.pricePerKilometer = pricePerKilometer;
    }
}


