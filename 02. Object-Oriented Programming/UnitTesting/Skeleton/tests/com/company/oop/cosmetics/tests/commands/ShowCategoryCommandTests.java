package com.company.oop.cosmetics.tests.commands;

import com.company.oop.cosmetics.commands.CreateCategoryCommand;
import com.company.oop.cosmetics.commands.ShowCategoryCommand;
import com.company.oop.cosmetics.commands.contracts.Command;
import com.company.oop.cosmetics.core.ProductRepositoryImpl;
import com.company.oop.cosmetics.core.contracts.ProductRepository;
import com.company.oop.cosmetics.exceptions.InvalidUserInputException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class ShowCategoryCommandTests {
    private static final String VALID_CATEGORY_NAME = "Shampoo";

    ProductRepository productRepository;
    Command showCategoryCommand;
    Command createCategory;

    @BeforeEach
    public void beforeEach() {
        productRepository = new ProductRepositoryImpl();
        showCategoryCommand = new ShowCategoryCommand(productRepository);
        createCategory = new CreateCategoryCommand(productRepository);
    }

    @Test
    public void execute_Should_ShowCategory_When_ValidParameters() {
        List<String> parameters = new ArrayList<>();
        parameters.add(VALID_CATEGORY_NAME);

        createCategory.execute(parameters);

        showCategoryCommand.execute(parameters);

        Assertions.assertEquals(1, productRepository.getCategories().size());
    }

    @Test
    public void execute_Should_ThrowException_When_MissingParameters() {
        List<String> parameters = new ArrayList<>();

        Assertions.assertThrows(InvalidUserInputException.class, () -> showCategoryCommand.execute(parameters));
    }

}
