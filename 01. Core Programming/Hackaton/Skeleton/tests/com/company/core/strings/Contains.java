package com.company.core.strings;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class Contains {
    
    @Test
    @DisplayName("contains() returns true when the given string contains the character")
    public void contains_true() {
        //Arrange
        String word = "Company";
        
        //Act, Assert
        Assertions.assertTrue(StringHelpers.contains(word, 'm'));
        
    }
    
    @Test
    @DisplayName("contains() returns false when the given string doesn't contain the character")
    public void contains_false() {
        //Arrange
        String word = "Company";
        
        //Act, Assert
        Assertions.assertFalse(StringHelpers.contains(word, 'q'));
        
    }
    
    @Test
    @DisplayName("contains() returns false when string is empty")
    public void contains_empty() {
        //Arrange
        String word = "";
        
        //Act, Assert
        Assertions.assertFalse(StringHelpers.contains(word, 'q'));
        
    }
    
}
