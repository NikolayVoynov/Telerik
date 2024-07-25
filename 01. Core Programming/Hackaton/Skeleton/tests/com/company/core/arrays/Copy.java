package com.company.core.arrays;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class Copy {
    
    @Test
    @DisplayName("copy() copies enough elements from source array")
    public void copy_sourceArray_bigger() {
        //Arrange
        int[] source = new int[]{1, 2, 3, 4, 5};
        int[] destination = new int[4];
        int[] expected = new int[]{1, 2, 3, 4};
        
        //Act
        ArrayHelpers.copy(source, destination, 4);
        
        //Assert
        Assertions.assertArrayEquals(expected, destination);
    }
    
    
    @Test
    @DisplayName("copy() fills the empty positions with default values")
    public void copy_destinationArray_bigger() {
        //Arrange
        int[] source = new int[]{1, 2, 3};
        int[] destination = new int[6];
        int[] expected = new int[]{1, 2, 3, 0, 0, 0};
        
        //Act
        ArrayHelpers.copy(source, destination, 6);
        
        //Assert
        Assertions.assertArrayEquals(expected, destination);
    }
    
}
