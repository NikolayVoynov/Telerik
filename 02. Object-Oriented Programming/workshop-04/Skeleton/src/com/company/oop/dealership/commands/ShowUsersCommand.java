package com.company.oop.dealership.commands;

import com.company.oop.dealership.core.contracts.VehicleDealershipRepository;
import com.company.oop.dealership.models.contracts.User;

import java.util.List;

public class ShowUsersCommand extends BaseCommand {

    private static final String USERS_HEADER = "--USERS--";
    private static final String SHOW_USERS_ERROR_MESSAGE = "You are not an admin!";

    public ShowUsersCommand(VehicleDealershipRepository vehicleDealershipRepository) {
        super(vehicleDealershipRepository);
    }

    @Override
    protected String executeCommand(List<String> parameters) {
        return showUsers();
    }

    private String showUsers() {
        if (!getVehicleDealershipRepository().getLoggedInUser().isAdmin()) {
            throw new IllegalArgumentException(SHOW_USERS_ERROR_MESSAGE);
        }

        StringBuilder result = new StringBuilder();

        result.append(USERS_HEADER);
        result.append(System.lineSeparator());

        int refNum = 1;

        for (User user : getVehicleDealershipRepository().getUsers()) {
            result.append(String.format("%d. %s", refNum, user.toString()));
            result.append(System.lineSeparator());

            refNum++;
        }

        return result.toString().trim();
    }

    @Override
    protected boolean requiresLogin() {
        return true;
    }
}