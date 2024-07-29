package com.company.oop.cosmetics.commands;

import com.company.oop.cosmetics.core.contracts.Command;
import com.company.oop.cosmetics.core.contracts.CosmeticsRepository;
import com.company.oop.cosmetics.models.enums.GenderType;
import com.company.oop.cosmetics.models.enums.UsageType;
import com.company.oop.cosmetics.utils.ParsingHelpers;
import com.company.oop.cosmetics.utils.ValidationHelpers;

import javax.xml.validation.ValidatorHandler;
import java.util.List;

public class CreateShampooCommand implements Command {
    public static final String INVALID_PRICE = "Invalid value for price. Should be a number.";
    public static final String INVALID_MILLILITRES = "Invalid value for millilitres. Should be a number.";
    public static final String PRODUCT_CREATED = "%s with name %s was created!";
    public static final String PRODUCT_NAME_ALREADY_EXISTS = "%s with name %s already exists!";

    public static final int EXPECTED_NUMBER_OF_ARGUMENTS = 6;

    private final CosmeticsRepository cosmeticsRepository;

    public CreateShampooCommand(CosmeticsRepository cosmeticsRepository) {
        this.cosmeticsRepository = cosmeticsRepository;
    }

    @Override
    public String execute(List<String> parameters) {
        ValidationHelpers.validateArgumentsCount(parameters, EXPECTED_NUMBER_OF_ARGUMENTS);
        String name = parameters.get(0);
        String brandName = parameters.get(1);
        double price = ParsingHelpers.tryParseDouble(parameters.get(2), INVALID_PRICE);
        GenderType genderType = ParsingHelpers.tryParseGender(parameters.get(3));
        int millilitres = ParsingHelpers.tryParseInt(parameters.get(4), INVALID_MILLILITRES);
        UsageType usageType = ParsingHelpers.tryParseUsageType(parameters.get(5));


        return createShampoo(name, brandName, price, genderType, millilitres, usageType);
    }

    private String createShampoo(String name, String brandName, double price, GenderType genderType,
                                 int millilitres, UsageType usageType) {
        if (cosmeticsRepository.productExist(name)) {
            throw new IllegalArgumentException(String.format(PRODUCT_NAME_ALREADY_EXISTS, "Shampoo", name));
        }

        cosmeticsRepository.createShampoo(name, brandName, price, genderType, millilitres, usageType);

        return String.format(PRODUCT_CREATED, "Shampoo", name);
    }

}
