package com.company.oop.cosmetics.tests.commands;

import com.company.oop.cosmetics.commands.CreateProductCommand;
import com.company.oop.cosmetics.commands.contracts.Command;
import com.company.oop.cosmetics.core.ProductRepositoryImpl;
import com.company.oop.cosmetics.core.contracts.ProductRepository;
import com.company.oop.cosmetics.exceptions.DuplicateEntityException;
import com.company.oop.cosmetics.exceptions.InvalidUserInputException;
import com.company.oop.cosmetics.models.GenderType;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class CreateProductCommandTests {
    public static final String VALID_NAME = "Name1";
    public static final String VALID_BRAND = "Brand1";
    public static final String VALID_PRICE = "2.0";

    ProductRepository productRepository;
    Command createProductCommand;

    @BeforeEach
    public void beforeEach() {
        productRepository = new ProductRepositoryImpl();
        createProductCommand = new CreateProductCommand(productRepository);
    }

    @Test
    public void execute_Should_AddNewProductToRepository_When_ValidParameters() {
        List<String> parameters = new ArrayList<>();
        parameters.add(VALID_NAME);
        parameters.add(VALID_BRAND);
        parameters.add(VALID_PRICE);
        parameters.add(GenderType.MEN.toString());

        createProductCommand.execute(parameters);

        Assertions.assertEquals(1, productRepository.getProducts().size());
    }

    @Test
    public void execute_Should_ThrowException_When_MissingParameters() {
        List<String> parameters = new ArrayList<>();
        parameters.add(VALID_NAME);
        parameters.add(VALID_BRAND);

        Assertions.assertThrows(InvalidUserInputException.class, () -> createProductCommand.execute(parameters));
    }

    @Test
    public void execute_Should_ThrowException_When_DuplicateProductName() {
        List<String> firstParameters = new ArrayList<>();
        firstParameters.add(VALID_NAME);
        firstParameters.add(VALID_BRAND);
        firstParameters.add(VALID_PRICE);
        firstParameters.add(GenderType.MEN.toString());

        List<String> secondParameters = new ArrayList<>();
        secondParameters.add(VALID_NAME);
        secondParameters.add(VALID_BRAND);
        secondParameters.add(VALID_PRICE);
        secondParameters.add(GenderType.MEN.toString());

        createProductCommand.execute(firstParameters);

        Assertions.assertThrows(DuplicateEntityException.class, () -> createProductCommand.execute(secondParameters));
    }

    @Test
    public void tryParseDouble_Should_ThrowException_When_InvalidPrice(){
        List<String> firstParameters = new ArrayList<>();
        firstParameters.add(VALID_NAME);
        firstParameters.add(VALID_BRAND);
        firstParameters.add("INVALID_PRICE");
        firstParameters.add(GenderType.MEN.toString());

        Assertions.assertThrows(InvalidUserInputException.class, () -> createProductCommand.execute(firstParameters));
    }

    @Test
    public void tryParseGenderType_Should_ThrowException_When_InvalidGenderType(){
        List<String> firstParameters = new ArrayList<>();
        firstParameters.add(VALID_NAME);
        firstParameters.add(VALID_BRAND);
        firstParameters.add(VALID_PRICE);
        firstParameters.add("INVALID GENDER");

        Assertions.assertThrows(InvalidUserInputException.class, () -> createProductCommand.execute(firstParameters));
    }

}
