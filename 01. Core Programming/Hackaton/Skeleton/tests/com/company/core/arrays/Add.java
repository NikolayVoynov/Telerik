package com.company.core.arrays;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class Add {
    
    @Test
    @DisplayName("add() adds at the end")
    public void addTest() {
        //Arrange
        int[] source = {1, 2, 3};
        int[] expected = {1, 2, 3, 4};
        
        //Act
        int[] result = ArrayHelpers.add(source, 4);
        
        //Assert
        Assertions.assertArrayEquals(result, expected);
    }
    
    @Test
    @DisplayName("add() returns an array with one element when given empty initial array")
    public void addTest_EmptyArray() {
        //Arrange
        int[] source = {};
        int[] expected = {1};
        
        //Act
        int[] result = ArrayHelpers.add(source, 1);
        
        //Assert
        Assertions.assertArrayEquals(result, expected);
    }
    
}
