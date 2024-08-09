package com.company.oop.cosmetics.tests.models;

import com.company.oop.cosmetics.exceptions.InvalidUserInputException;
import com.company.oop.cosmetics.models.CategoryImpl;
import com.company.oop.cosmetics.models.GenderType;
import com.company.oop.cosmetics.models.ProductImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class CategoryImplTests {


    @Test
    public void constructor_Should_InitializeName_When_ArgumentsAreValid() {
        CategoryImpl category = new CategoryImpl("Shampoo");
        String validName = "Shampoo";

        Assertions.assertEquals(validName, category.getName());
    }

    @Test
    public void constructor_Should_InitializeProducts_When_ArgumentsAreValid() {
        CategoryImpl category = new CategoryImpl("Shampoo");

        Assertions.assertEquals(0, category.getProducts().size());
    }

    @Test
    public void constructor_Should_ThrowException_When_NameIsShorterThanExpected() {

        Assertions.assertThrows(InvalidUserInputException.class, () -> new CategoryImpl("xx"));
    }

    @Test
    public void addProduct_Should_AddProductToList() {
        CategoryImpl category = new CategoryImpl("Shampoo");
        ProductImpl product = new ProductImpl("Name", "Brand", 1, GenderType.MEN);
        category.addProduct(product);

        Assertions.assertEquals(1, category.getProducts().size());
    }

    @Test
    public void removeProduct_Should_RemoveProductFromList_When_ProductExist() {
        CategoryImpl category = new CategoryImpl("Shampoo");
        ProductImpl product1 = new ProductImpl("Name1", "Brand1", 1, GenderType.MEN);
        ProductImpl product2 = new ProductImpl("Name2", "Brand2", 1, GenderType.MEN);

        category.addProduct(product1);
        category.addProduct(product2);
        category.removeProduct(product1);

        Assertions.assertEquals(1, category.getProducts().size());
        Assertions.assertTrue(category.getProducts().contains(product2));
    }

    @Test
    public void removeProduct_should_notRemoveProductFromList_when_productNotExist() {
        CategoryImpl category = new CategoryImpl("Shampoo");
        ProductImpl product1 = new ProductImpl("Name1", "Brand1", 1, GenderType.MEN);
        ProductImpl product2 = new ProductImpl("Name2", "Brand2", 1, GenderType.MEN);
        ProductImpl product3 = new ProductImpl("Name3", "Brand3", 1, GenderType.WOMEN);

        category.addProduct(product1);
        category.addProduct(product2);
        category.removeProduct(product3);

        Assertions.assertEquals(2, category.getProducts().size());
    }

}
