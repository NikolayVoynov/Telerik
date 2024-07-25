package com.company.core.arrays;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class RemoveAllOccurrences {
    
    @Test
    @DisplayName("removeAllOccurrences() removes when element exists")
    public void removeAllOccurrences_removes_when_elementExists() {
        //Arrange
        int[] source = new int[]{1, 2, 3, 2, 4, 2, 5, 6};
        int[] expected = new int[]{1, 3, 4, 5, 6};
        
        //Act
        int[] result = ArrayHelpers.removeAllOccurrences(source, 2);
        
        //Assert
        Assertions.assertArrayEquals(expected, result);
    }
    
    @Test
    @DisplayName("removeAllOccurrences() returns unchanged array when element doesn't exist")
    public void removeAllOccurrences_returnsUnchangedArray_when_elementDoesntExist() {
        //Arrange
        int[] source = new int[]{1, 2, 3, 2, 4, 2, 5, 6};
        int[] expected = new int[]{1, 2, 3, 2, 4, 2, 5, 6};
        
        //Act
        int[] result = ArrayHelpers.removeAllOccurrences(source, 7);
        
        //Assert
        Assertions.assertArrayEquals(expected, result);
    }
    
}
