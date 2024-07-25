package com.company.core.arrays;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class Insert {
    
    @Test
    @DisplayName("insert() inserts the given element at the given index")
    public void insert() {
        //Arrange
        int[] source = new int[]{1, 2, 3};
        int[] expected = new int[]{1, 4, 2, 3};
        
        //Act
        int[] result = ArrayHelpers.insert(source, 1, 4);
        
        //Assert
        Assertions.assertArrayEquals(result, expected);
    }
    
    @Test
    @DisplayName("insert() inserts the given element at the head")
    public void insert_head_position() {
        //Arrange
        int[] source = new int[]{1, 2, 3};
        int[] expected = new int[]{4, 1, 2, 3};
        
        //Act
        int[] result = ArrayHelpers.insert(source, 0, 4);
        
        //Assert
        Assertions.assertArrayEquals(result, expected);
    }
    
    @Test
    @DisplayName("insert() inserts the given element at the end")
    public void insert_tail_position() {
        //Arrange
        int[] source = new int[]{1, 2, 3};
        int[] expected = new int[]{1, 2, 3, 4};
        
        //Act
        int[] result = ArrayHelpers.insert(source, source.length, 4);
        
        //Assert
        Assertions.assertArrayEquals(result, expected);
    }
    
}
