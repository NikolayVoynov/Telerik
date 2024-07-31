package com.company.oop.agency.models.vehicles;

public enum VehicleType {
    LAND,
    AIR,
    SEA;

    @Override
    public String toString() {
        switch (this) {
            case AIR:
                return "AIR";
            case LAND:
                return "LAND";
            case SEA:
                return "SEA";
            default:
                throw new IllegalArgumentException("There is no such vehicle type!");
        }
    }
}
