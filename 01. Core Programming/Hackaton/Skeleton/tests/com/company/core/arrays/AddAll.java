package com.company.core.arrays;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class AddAll {
    
    @Test
    @DisplayName("addAll() appends new elements at the end")
    public void addAllTest() {
        //Arrange
        int[] source = {1, 2, 3};
        int[] expected = {1, 2, 3, 4, 5, 6};
        
        //Act
        int[] result = ArrayHelpers.addAll(source, 4, 5, 6);
        
        //Assert
        Assertions.assertArrayEquals(result, expected);
    }
    
    @Test
    @DisplayName("addAll() returns array of the new elements if source is empty")
    public void addAllTest_EmptyArray() {
        //Arrange
        int[] source = {};
        int[] expected = {1, 2, 3};
        
        //Act
        int[] result = ArrayHelpers.addAll(source, 1, 2, 3);
        
        //Assert
        Assertions.assertArrayEquals(result, expected);
    }
    
    
    @Test
    @DisplayName("addAll() returns unchanged array if no new elements provided")
    public void addAllTest_noNewElements() {
        //Arrange
        int[] source = {1, 2, 3};
        int[] expected = {1, 2, 3};
        
        //Act
        int[] result = ArrayHelpers.addAll(source);
        
        //Assert
        Assertions.assertArrayEquals(result, expected);
    }
    
}
