package com.company.oop.cosmetics.tests.commands;

import com.company.oop.cosmetics.commands.AddToShoppingCartCommand;
import com.company.oop.cosmetics.core.CosmeticsRepositoryImpl;
import com.company.oop.cosmetics.core.contracts.Command;
import com.company.oop.cosmetics.core.contracts.CosmeticsRepository;
import com.company.oop.cosmetics.models.contracts.Product;
import com.company.oop.cosmetics.tests.models.ShampooTests;
import com.company.oop.cosmetics.tests.utils.TestUtilities;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class AddToShoppingCartCommandTests {

    public static final int EXPECTED_NUMBER_OF_ARGUMENTS = 1;

    private Command addToShoppingCartCommand;
    private CosmeticsRepository cosmeticsRepository;

    @BeforeEach
    public void before() {
        cosmeticsRepository = new CosmeticsRepositoryImpl();
        addToShoppingCartCommand = new AddToShoppingCartCommand(cosmeticsRepository);
    }

    @Test
    public void should_ThrowException_When_ArgumentCountDifferentThanExpected() {
        // Arrange
        List<String> params = TestUtilities.getList(EXPECTED_NUMBER_OF_ARGUMENTS - 1);

        // Act, Assert
        assertThrows(IllegalArgumentException.class, () -> addToShoppingCartCommand.execute(params));
    }

    @Test
    public void should_ThrowException_When_ProductDoesNotExist() {
        // Arrange
        List<String> params = List.of(ShampooTests.VALID_SHAMPOO_NAME);

        // Act, Assert
        assertThrows(IllegalArgumentException.class, () -> addToShoppingCartCommand.execute(params));
    }

    @Test
    public void should_AddProductToShoppingCart_When_ArgumentsAreValid() {
        // Arrange
        Product product = ShampooTests.addInitializedShampooToRepository(cosmeticsRepository);
        List<String> params = List.of(product.getName());
        addToShoppingCartCommand.execute(params);

        // Act, Assert
        assertEquals(1, cosmeticsRepository.getShoppingCart().getProducts().size());
    }
}
