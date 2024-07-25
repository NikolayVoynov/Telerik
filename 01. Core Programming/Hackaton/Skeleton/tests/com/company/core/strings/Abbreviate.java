package com.company.core.strings;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class Abbreviate {
    
    @Test
    @DisplayName("abbreviate() returns abbreviated string")
    public void abbreviate_abbreviates_input() {
        //Arrange
        String word = "Company";
        String expected = "Compan...";
        
        //Act
        String result = StringHelpers.abbreviate(word, 6);
        
        //Assert
        Assertions.assertEquals(expected, result);
    }
    
    @Test
    @DisplayName("abbreviate() returns unchanged string when given longer length")
    public void abbreviate_returns_unchanged_when_longerLength() {
        //Arrange
        String word = "Company";
        String expected = "Company";
        
        //Act
        String result = StringHelpers.abbreviate(word, 30);
        
        //Assert
        Assertions.assertEquals(expected, result);
    }
    
    @Test
    @DisplayName("abbreviate() returns empty string when passed empty string")
    public void abbreviate_returns_empty_when_emptyString() {
        //Arrange
        String word = "";
        String expected = "";
        
        //Act
        String result = StringHelpers.abbreviate(word, 30);
        
        //Assert
        Assertions.assertEquals(expected, result);
    }
    
}
