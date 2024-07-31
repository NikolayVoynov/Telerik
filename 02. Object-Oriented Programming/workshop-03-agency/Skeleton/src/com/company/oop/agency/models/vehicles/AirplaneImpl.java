package com.company.oop.agency.models.vehicles;

import com.company.oop.agency.models.vehicles.contracts.Airplane;

public class AirplaneImpl extends VehicleImpl implements Airplane {

    private boolean hasFreeFood;
    private VehicleType vehicleType;

    public AirplaneImpl(int id, int passengerCapacity, double pricePerKilometer, boolean hasFreeFood) {
        super(id, passengerCapacity, pricePerKilometer);
        this.vehicleType = VehicleType.AIR;
        this.hasFreeFood = hasFreeFood;
    }

    @Override
    public VehicleType getType() {
        return vehicleType;
    }

    @Override
    public boolean hasFreeFood() {
        return hasFreeFood;
    }

    @Override
    public String getAsString() {
        return String.format("Airplane ----%n" +
                super.getAsString() +
                "Has free food: %b%n", hasFreeFood);
    }
}