package com.company.core.arrays;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class FirstIndexOf {
    
    @Test
    @DisplayName("firstIndexOf() should return -1 when the element is not in the array")
    public void firstIndexOf_element_not_found() {
        //Arrange
        int[] source = new int[]{1, 2, 3};
        int expected = -1;
        
        //Act, Assert
        Assertions.assertEquals(ArrayHelpers.firstIndexOf(source, 7), expected);
        
    }
    
    @Test
    @DisplayName("firstIndexOf() should return the index when the element is in the array")
    public void firstIndexOf_element_found() {
        //Arrange
        int[] source = new int[]{1, 2, 3};
        int expected = 1;
        
        //Act, Assert
        Assertions.assertEquals(ArrayHelpers.firstIndexOf(source, 2), expected);
        
    }
    
    @Test
    @DisplayName("firstIndexOf() should return the index when the element is in the array")
    public void firstIndexOf_element_found2() {
        //Arrange
        int[] source = new int[]{1, 2, 3, 4, 5, 6, 7};
        int expected = 3;
        
        //Act, Assert
        Assertions.assertEquals(ArrayHelpers.firstIndexOf(source, 4), expected);
        
    }
    
}
