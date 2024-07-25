package com.company.core.arrays;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class AddFirst {
    
    @Test
    @DisplayName("addFirst() adds a new element at the begging")
    public void addFirstTest() {
        //Arrange
        int[] source = {1, 2, 3};
        int[] expected = {4, 1, 2, 3};
        
        //Act
        int[] result = ArrayHelpers.addFirst(source, 4);
        
        //Assert
        Assertions.assertArrayEquals(result, expected);
    }
    
    @Test
    @DisplayName("addFirst() returns an array with one element when given empty initial array")
    public void addFirstTest_EmptyArray() {
        //Arrange
        int[] source = {};
        int[] expected = {1};
        
        //Act
        int[] result = ArrayHelpers.addFirst(source, 1);
        
        //Assert
        Assertions.assertArrayEquals(result, expected);
    }
    
}
