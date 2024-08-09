package com.company.oop.cosmetics.tests.core;

import com.company.oop.cosmetics.core.ProductRepositoryImpl;
import com.company.oop.cosmetics.core.contracts.ProductRepository;
import com.company.oop.cosmetics.exceptions.InvalidUserInputException;
import com.company.oop.cosmetics.models.GenderType;
import com.company.oop.cosmetics.models.contracts.Category;
import com.company.oop.cosmetics.models.contracts.Product;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

public class ProductRepositoryImplTests {

    ProductRepository productRepository;

    @BeforeEach
    public void beforeEach() {
        productRepository = new ProductRepositoryImpl();
    }

    @Test
    public void constructor_Should_InitializeProducts() {
        Assertions.assertNotNull(productRepository.getProducts());
    }

    @Test
    public void constructor_Should_InitializeCategories() {
        Assertions.assertNotNull(productRepository.getCategories());
    }

    @Test
    public void getCategories_Should_ReturnCopyOfCollection() {
        List<Category> firstCopyCollection = productRepository.getCategories();
        List<Category> secondCopyCollection = productRepository.getCategories();

        Assertions.assertNotSame(firstCopyCollection, secondCopyCollection);
    }

    @Test
    public void getProducts_Should_ReturnCopyOfCollection() {
        List<Product> firstCopyCollection = productRepository.getProducts();
        List<Product> secondCopyCollection = productRepository.getProducts();

        Assertions.assertNotSame(firstCopyCollection, secondCopyCollection);
    }

    @Test
    public void categoryExists_Should_ReturnTrue_When_CategoryExists() {
        String categoryName = "Shampoo";
        productRepository.createCategory(categoryName);

        Assertions.assertTrue(productRepository.categoryExist(categoryName));
    }

    @Test
    public void categoryExists_Should_ReturnFalse_When_CategoryDoesNotExist() {
        String categoryName = "Shampoo";
        Assertions.assertFalse(productRepository.categoryExist(categoryName));
    }

    @Test
    public void productExists_Should_ReturnTrue_When_ProductExists() {
        String productName = "ForMen";
        String productBrand = "Nivea";
        double price = 3.0;
        productRepository.createProduct(productName, productBrand, price, GenderType.MEN);

        Assertions.assertTrue(productRepository.productExist(productName));
    }

    @Test
    public void productExists_Should_ReturnFalse_When_ProductDoesNotExist() {
        String productName = "ForMen";

        Assertions.assertFalse(productRepository.productExist(productName));
    }

    @Test
    public void createProduct_Should_AddToProducts_When_ArgumentsAreValid() {
        String productName = "ForMen";
        String productBrand = "Nivea";
        double price = 3.0;
        productRepository.createProduct(productName, productBrand, price, GenderType.MEN);

        Assertions.assertEquals(1, productRepository.getProducts().size());
    }

    @Test
    public void createCategory_Should_AddToCategories_When_ArgumentsAreValid() {
        String categoryName = "Shampoo";
        productRepository.createCategory(categoryName);

        Assertions.assertEquals(1, productRepository.getCategories().size());
    }

    @Test
    public void findCategoryByName_Should_ReturnCategory_When_Exists() {
        String categoryName = "Shampoo";
        productRepository.createCategory(categoryName);

        Assertions.assertNotNull(productRepository.findCategoryByName(categoryName));
    }

    @Test
    public void findCategoryByName_Should_ThrowException_When_DoesNotExist() {
        String categoryName = "Shampoo";

        Assertions.assertThrows(InvalidUserInputException.class,
                () -> productRepository.findCategoryByName(categoryName));
    }

    @Test
    public void findProductByName_Should_ReturnProduct_When_Exists() {
        String productName = "ForMen";
        String productBrand = "Nivea";
        double price = 3.0;
        productRepository.createProduct(productName, productBrand, price, GenderType.MEN);

        Assertions.assertNotNull(productRepository.findProductByName(productName));
    }

    @Test
    public void findProductByName_Should_ThrowException_When_DoesNotExist() {
        String productName = "ForMen";

        Assertions.assertThrows(InvalidUserInputException.class,
                () -> productRepository.findProductByName(productName));
    }

}
