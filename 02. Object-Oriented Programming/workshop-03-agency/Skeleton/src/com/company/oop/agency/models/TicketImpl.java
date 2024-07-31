package com.company.oop.agency.models;

import com.company.oop.agency.models.contracts.Journey;
import com.company.oop.agency.models.contracts.Ticket;
import com.company.oop.agency.utils.ValidationHelper;

public class TicketImpl implements Ticket {

    private int id;
    private Journey journey;
    private double administrativeCosts;

    public TicketImpl(int id, Journey journey, double costs) {
        setAdministrativeCosts(costs);
        this.id = id;
        this.journey = journey;
    }

    @Override
    public int getId() {
        return id;
    }

    @Override
    public Journey getJourney() {
        return journey;
    }

    @Override
    public double getAdministrativeCosts() {
        return administrativeCosts;
    }

    @Override
    public double calculatePrice() {
        return administrativeCosts * journey.calculateTravelCosts();
    }

    @Override
    public String getAsString() {
        return String.format("Ticket ----%n" +
                        "Destination: %s%n" +
                        "Price: %.2f%n",
                journey.getDestination(), calculatePrice());
    }

    private void setAdministrativeCosts(double administrativeCosts) {
        ValidationHelper.validatePositiveValue(administrativeCosts);

        this.administrativeCosts = administrativeCosts;
    }
}
