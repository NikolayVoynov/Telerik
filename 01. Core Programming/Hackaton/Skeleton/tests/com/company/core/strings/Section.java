package com.company.core.strings;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class Section {
    
    @Test
    @DisplayName("section() returns the correct part of the source string")
    public void section() {
        //Arrange
        String source = "xxCompanyxx";
        String expected = "Company";
        
        //Act
        String result = StringHelpers.section(source, 2, 8);
        
        //Assert
        Assertions.assertEquals(expected, result);
    }
    
}
