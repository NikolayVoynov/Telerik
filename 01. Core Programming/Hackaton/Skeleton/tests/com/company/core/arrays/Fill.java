package com.company.core.arrays;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class Fill {
    
    @Test
    @DisplayName("fill() fills the array with the given element")
    public void fill_test() {
        //Arrange
        int[] source = new int[4];
        int[] expected = new int[]{1, 1, 1, 1};
        
        //Act
        ArrayHelpers.fill(source, 1);
        
        //Assert
        Assertions.assertArrayEquals(expected, source);
    }
    
}
