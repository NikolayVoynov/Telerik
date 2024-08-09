package com.company.oop.cosmetics.tests.commands;

import com.company.oop.cosmetics.commands.CreateCategoryCommand;
import com.company.oop.cosmetics.commands.contracts.Command;
import com.company.oop.cosmetics.core.ProductRepositoryImpl;
import com.company.oop.cosmetics.core.contracts.ProductRepository;
import com.company.oop.cosmetics.exceptions.DuplicateEntityException;
import com.company.oop.cosmetics.exceptions.InvalidUserInputException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class CreateCategoryCommandTests {
    private static final String VALID_CATEGORY_NAME = "Shampoo";

    ProductRepository productRepository;
    Command createCategoryCommand;

    @BeforeEach
    public void beforeEach() {
        productRepository = new ProductRepositoryImpl();
        createCategoryCommand = new CreateCategoryCommand(productRepository);
    }

    @Test
    public void execute_Should_AddNewCategoryToRepository_When_ValidParameters() {
        List<String> parameters = new ArrayList<>();
        parameters.add(VALID_CATEGORY_NAME);

        createCategoryCommand.execute(parameters);

        Assertions.assertEquals(1, productRepository.getCategories().size());
    }

    @Test
    public void execute_Should_ThrowException_When_MissingParameters() {
        List<String> parameters = new ArrayList<>();

        Assertions.assertThrows(InvalidUserInputException.class, () -> createCategoryCommand.execute(parameters));
    }

    @Test
    public void execute_Should_ThrowException_When_DuplicateCategoryName() {
        List<String> firstParameters = new ArrayList<>();
        firstParameters.add(VALID_CATEGORY_NAME);

        List<String> secondParameters = new ArrayList<>();
        secondParameters.add(VALID_CATEGORY_NAME);

        createCategoryCommand.execute(firstParameters);

        Assertions.assertThrows(DuplicateEntityException.class, () -> createCategoryCommand.execute(secondParameters));
    }


}
