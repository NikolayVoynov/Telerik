package com.company.core.strings;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class Capitalize {
    
    @Test
    @DisplayName("capitalize() returns capitalized string")
    public void capitalize_returns_capitalizedString() {
        //Arrange
        String source = "java";
        String expected = "Java";
        
        //Act
        String result = StringHelpers.capitalize(source);
        
        //Assert
        Assertions.assertEquals(expected, result);
    }
    
    @Test
    @DisplayName("capitalize() doesn't change other characters")
    public void capitalize_1() {
        //Arrange
        String source = "jAva";
        String expected = "JAva";
        
        //Act
        String result = StringHelpers.capitalize(source);
        
        //Assert
        Assertions.assertEquals(expected, result);
    }
    
    @Test
    @DisplayName("capitalize() returns empty string when passed an empty string")
    public void capitalize_returns_empty_when_stringEmpty() {
        //Arrange
        String source = "";
        String expected = "";
        
        //Act
        String result = StringHelpers.capitalize(source);
        
        //Assert
        Assertions.assertEquals(expected, result);
    }
    
}
