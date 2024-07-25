package com.company.core.arrays;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class Contains {
    
    @Test
    @DisplayName("contains() returns true when the array contains the element")
    public void contains_true() {
        //Arrange
        int[] source = {1, 2, 3};
        
        //Act
        boolean result = ArrayHelpers.contains(source, 1);
        
        //Assert
        Assertions.assertTrue(result);
    }
    
    @Test
    @DisplayName("contains() returns false when the array doesn't contain the element")
    public void contains_false() {
        //Arrange
        int[] source = {1, 2, 3};
        
        //Act, Assert
        Assertions.assertFalse(ArrayHelpers.contains(source, 4));
    }
    
}
