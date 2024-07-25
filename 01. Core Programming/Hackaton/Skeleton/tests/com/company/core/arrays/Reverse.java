package com.company.core.arrays;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class Reverse {
    
    @Test
    @DisplayName("reverse() reverses the given array")
    public void reverse() {
        //Arrange
        int[] source = {1, 2, 3, 4, 5};
        int[] expected = {5, 4, 3, 2, 1};
        
        //Act
        ArrayHelpers.reverse(source);
        
        //Assert
        Assertions.assertArrayEquals(source, expected);
    }
    
}
