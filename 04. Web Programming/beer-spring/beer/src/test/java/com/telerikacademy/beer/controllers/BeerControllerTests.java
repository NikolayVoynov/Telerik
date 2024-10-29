package com.telerikacademy.beer.controllers;

import com.telerikacademy.beer.services.BeerService;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static com.telerikacademy.beer.Helpers.createMockBeer;

@SpringBootTest
@AutoConfigureMockMvc
public class BeerControllerTests {

    @Mock
    BeerService mockBeerService;

    @Autowired
    MockMvc mockMvc;

    @Test
    public void getBeerById_Should_Return_StatusOK_When_BeerExists() throws Exception {
        // Arrange

        var mockBeer = createMockBeer();
        Mockito.when(mockBeerService.getBeerById(Mockito.anyInt()))
                .thenReturn(mockBeer);

        mockMvc.perform(MockMvcRequestBuilders.get("/api/beers/{id}", mockBeer.getId()))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value(mockBeer.getName()));
    }

}
