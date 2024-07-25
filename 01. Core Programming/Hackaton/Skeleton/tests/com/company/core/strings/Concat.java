package com.company.core.strings;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class Concat {
    
    @Test
    @DisplayName("concat() concatenates two strings")
    public void concat_concatenates() {
        //Arrange
        String word1 = "New";
        String word2 = " Company";
        String expected = "New Company";
        
        //Act
        String result = StringHelpers.concat(word1, word2);
        
        //Assert
        Assertions.assertEquals(expected, result);
    }
    
    @Test
    @DisplayName("concat() returns only second string if first is empty")
    public void concat_empty() {
        //Arrange
        String word1 = "";
        String word2 = "Company";
        String expected = "Company";
        
        //Act
        String result = StringHelpers.concat(word1, word2);
        
        //Assert
        Assertions.assertEquals(expected, result);
    }
    
}
