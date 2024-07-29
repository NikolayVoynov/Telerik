package com.company.oop.cosmetics.commands;

import com.company.oop.cosmetics.core.contracts.Command;
import com.company.oop.cosmetics.core.contracts.CosmeticsRepository;
import com.company.oop.cosmetics.models.enums.GenderType;
import com.company.oop.cosmetics.utils.ParsingHelpers;
import com.company.oop.cosmetics.utils.ValidationHelpers;

import java.util.ArrayList;
import java.util.List;

public class CreateToothpasteCommand implements Command {
    public static final String INVALID_PRICE = "Invalid value for price. Should be a number.";
    public static final String PRODUCT_CREATED = "%s with name %s was created!";
    public static final String PRODUCT_NAME_ALREADY_EXISTS = "%s with name %s already exists!";
    public static final int EXPECTED_NUMBER_OF_ARGUMENTS = 5;

    private final CosmeticsRepository cosmeticsRepository;

    public CreateToothpasteCommand(CosmeticsRepository cosmeticsRepository) {
        this.cosmeticsRepository = cosmeticsRepository;
    }

    @Override
    public String execute(List<String> parameters) {
        ValidationHelpers.validateArgumentsCount(parameters, EXPECTED_NUMBER_OF_ARGUMENTS);
        String name = parameters.get(0);
        String brandName = parameters.get(1);
        double price = ParsingHelpers.tryParseDouble(parameters.get(2), INVALID_PRICE);
        GenderType genderType = ParsingHelpers.tryParseGender(parameters.get(3));

        String[] arrIngredients = parameters.get(4).split(",");

        List<String> ingredients = new ArrayList<>();

        for (int i = 0; i < arrIngredients.length; i++) {
            ingredients.add(arrIngredients[i]);
        }

        return createToothpaste(name, brandName, price, genderType, ingredients);
    }

    private String createToothpaste(String name, String brandName, double price,
                                    GenderType genderType, List<String> ingredients) {
        if (cosmeticsRepository.productExist(name)) {
            throw new IllegalArgumentException(String.format(PRODUCT_NAME_ALREADY_EXISTS, "Toothpaste", name));
        }

        cosmeticsRepository.createToothpaste(name, brandName, price, genderType, ingredients);

        return String.format(PRODUCT_CREATED, "Toothpaste", name);
    }

}