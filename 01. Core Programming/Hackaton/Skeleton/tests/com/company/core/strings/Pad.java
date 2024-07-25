package com.company.core.strings;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class Pad {
    
    @Test
    @DisplayName("pad() pads the given string from both sides")
    public void pad() {
        //Arrange
        String source = "Company";
        char paddingSymbol = '-';
        String expected = "---Company---";
        
        //Act
        String result = StringHelpers.pad(source,
                expected.length(),
                paddingSymbol);
        
        //Assert
        Assertions.assertEquals(expected, result);
    }
    
    @Test
    @DisplayName("pad() pads evenly from both sides")
    public void pad_odd_length() {
        //Arrange
        String source = "Company";
        char paddingSymbol = '-';
        String expected = "-Company-";
        
        //Act
        String result = StringHelpers.pad(source,
                expected.length(),
                paddingSymbol);
        
        System.out.println(result);
        
        //Assert
        Assertions.assertEquals(expected, result);
    }
    
    
    @Test
    @DisplayName("pad() returns source when source is longer than length")
    public void pad_returnsSource_when_longer() {
        //Arrange
        String source = "Company";
        char paddingSymbol = '-';
        String expected = "Company";
        
        //Act
        String result = StringHelpers.pad(source,
                1,
                paddingSymbol);
        
        System.out.println(result);
        
        //Assert
        Assertions.assertEquals(expected, result);
    }
    
}
