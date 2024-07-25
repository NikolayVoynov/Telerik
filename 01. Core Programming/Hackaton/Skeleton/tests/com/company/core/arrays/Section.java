package com.company.core.arrays;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class Section {
    
    @Test
    @DisplayName("section() returns modified array")
    public void section_returns_modifiedArray() {
        //Arrange
        int[] source = new int[]{1, 2, 3, 4, 5, 6};
        int[] expected = new int[]{2, 3, 4, 5};
        
        //Act
        int[] result = ArrayHelpers.section(source, 1, 4);
        
        //Assert
        Assertions.assertArrayEquals(expected, result);
    }
    
    @Test
    @DisplayName("section() returns modified array starting from 0")
    public void section_returns_modifiedArray_start_head() {
        //Arrange
        int[] source = new int[]{1, 2, 3, 4, 5, 6};
        int[] expected = new int[]{1, 2, 3, 4, 5};
        
        //Act
        int[] result = ArrayHelpers.section(source, 0, 4);
        
        //Assert
        Assertions.assertArrayEquals(expected, result);
    }
    
    @Test
    @DisplayName("section() returns empty array")
    public void section_returns_modifiedArray_empty_source() {
        //Arrange
        int[] source = new int[]{};
        int[] expected = new int[]{};
        
        //Act
        int[] result = ArrayHelpers.section(source, 0, 4);
        
        //Assert
        Assertions.assertArrayEquals(expected, result);
    }
    
    @Test
    @DisplayName("section() takes all remaining when given longer length")
    public void section_returns_modifiedArray_longerLength() {
        //Arrange
        int[] source = new int[]{1, 2, 3, 4, 5, 6};
        int[] expected = new int[]{3, 4, 5, 6};
        
        //Act
        int[] result = ArrayHelpers.section(source, 2, 10);
        
        //Assert
        Assertions.assertArrayEquals(expected, result);
    }
    
    @Test
    @DisplayName("section() returns source array when given invalid startIndex")
    public void section_returns_sourceArray_invalidIndex() {
        //Arrange
        int[] source = new int[]{1, 2, 3};
        int[] expected = new int[]{1, 2, 3};
        
        //Act
        int[] result = ArrayHelpers.section(source, 10, 1);
        
        //Assert
        Assertions.assertArrayEquals(expected, result);
    }
    
}
