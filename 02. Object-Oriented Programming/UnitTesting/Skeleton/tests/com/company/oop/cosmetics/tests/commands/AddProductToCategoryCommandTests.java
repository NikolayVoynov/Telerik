package com.company.oop.cosmetics.tests.commands;

import com.company.oop.cosmetics.commands.AddProductToCategoryCommand;
import com.company.oop.cosmetics.commands.CreateCategoryCommand;
import com.company.oop.cosmetics.commands.CreateProductCommand;
import com.company.oop.cosmetics.commands.contracts.Command;
import com.company.oop.cosmetics.core.ProductRepositoryImpl;
import com.company.oop.cosmetics.core.contracts.ProductRepository;
import com.company.oop.cosmetics.exceptions.InvalidUserInputException;
import com.company.oop.cosmetics.models.GenderType;
import com.company.oop.cosmetics.models.contracts.Category;
import com.company.oop.cosmetics.models.contracts.Product;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class AddProductToCategoryCommandTests {
    private static final String VALID_CATEGORY_NAME = "Cream";
    public static final String VALID_PRODUCT_NAME = "Nivea";
    public static final String VALID_BRAND = "Brand1";
    public static final String VALID_PRICE = "2.0";

    Command addProductToCategoryCommand;
    Command createCategoryCommand;
    Command createProductCommand;
    ProductRepository productRepository;

    @BeforeEach
    public void beforeEach() {
        productRepository = new ProductRepositoryImpl();
        addProductToCategoryCommand = new AddProductToCategoryCommand(productRepository);
        createCategoryCommand = new CreateCategoryCommand(productRepository);
        createProductCommand = new CreateProductCommand(productRepository);
    }

    @Test
    public void execute_Should_AddProductToCategory_When_ValidParameters() {

        List<String> categoryParamList = new ArrayList<>();
        categoryParamList.add(VALID_CATEGORY_NAME);

        createCategoryCommand.execute(categoryParamList);


        List<String> productParamList = new ArrayList<>();
        productParamList.add(VALID_PRODUCT_NAME);
        productParamList.add(VALID_BRAND);
        productParamList.add(VALID_PRICE);
        productParamList.add(GenderType.MEN.toString());

        createProductCommand.execute(productParamList);


        List<String> parameters = new ArrayList<>();
        parameters.add(VALID_CATEGORY_NAME);
        parameters.add(VALID_PRODUCT_NAME);

        addProductToCategoryCommand.execute(parameters);

        Category category = productRepository.findCategoryByName(VALID_CATEGORY_NAME);
        Product product = productRepository.findProductByName(VALID_PRODUCT_NAME);

        Assertions.assertTrue(category.getProducts().contains(product));
    }

    @Test
    public void execute_Should_ThrowException_When_MissingParameters() {
        List<String> params = new ArrayList<>();

        Assertions.assertThrows(InvalidUserInputException.class, () -> addProductToCategoryCommand.execute(params));
    }


}
