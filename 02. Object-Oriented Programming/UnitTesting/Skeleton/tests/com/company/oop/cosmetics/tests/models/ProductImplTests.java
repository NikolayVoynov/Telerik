package com.company.oop.cosmetics.tests.models;

import com.company.oop.cosmetics.exceptions.InvalidUserInputException;
import com.company.oop.cosmetics.models.GenderType;
import com.company.oop.cosmetics.models.ProductImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ProductImplTests {
    public static final String NAME_1 = "Name1";
    public static final String BRAND_1 = "Brand1";
    public static final String BRAND_2 = "Brand2";
    public static final String NAME_2 = "Name2";
    private ProductImpl product;

    @BeforeEach
    public void before() {
        product = new ProductImpl(
                NAME_1,
                BRAND_1,
                1,
                GenderType.MEN);
    }

    @Test
    public void constructor_ShouldInitializeProduct_When_ArgumentsAreValid() {
        Assertions.assertEquals(NAME_1, product.getName());
        Assertions.assertEquals(BRAND_1, product.getBrand());
        Assertions.assertEquals(1, product.getPrice());
        Assertions.assertEquals(GenderType.MEN, product.getGender());
    }

    @Test
    public void constructor_Should_ThrowException_When_NameIsShorterThanExpected() {
        Assertions.assertThrows(InvalidUserInputException.class,
                () -> new ProductImpl("xx", BRAND_2, 2, GenderType.MEN));
    }

    @Test
    public void constructor_Should_ThrowException_When_NameIsLongerThanExpected() {
        Assertions.assertThrows(InvalidUserInputException.class,
                () -> new ProductImpl("xxxxxxxxxxx", BRAND_2, 2, GenderType.MEN));
    }

    @Test
    public void constructor_Should_ThrowException_When_BrandIsShorterThanExpected() {
        Assertions.assertThrows(InvalidUserInputException.class,
                () -> new ProductImpl(NAME_2, "x", 2, GenderType.MEN));
    }

    @Test
    public void constructor_Should_ThrowException_When_BrandIsLongerThanExpected() {
        Assertions.assertThrows(InvalidUserInputException.class,
                () -> new ProductImpl(NAME_2, "xxxxxxxxxxxxxxxxx", 2, GenderType.MEN));
    }

    @Test
    public void constructor_Should_ThrowException_When_PriceNegative() {
        Assertions.assertThrows(InvalidUserInputException.class,
                () -> new ProductImpl(NAME_2, BRAND_2, -20, GenderType.WOMEN));
    }
}
