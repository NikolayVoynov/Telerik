package com.telerikacademy.beertag.controllers;

import com.telerikacademy.beertag.exceptions.EntityNotFoundException;
import com.telerikacademy.beertag.helpers.AuthenticationHelper;
import com.telerikacademy.beertag.helpers.BeerMapper;
import com.telerikacademy.beertag.models.Beer;
import com.telerikacademy.beertag.models.BeerDto;
import com.telerikacademy.beertag.models.User;
import com.telerikacademy.beertag.services.BeerService;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.web.server.ResponseStatusException;

import static com.telerikacademy.beertag.Helper.*;

@SpringBootTest
@AutoConfigureMockMvc
public class BeerControllerTests {

    @MockBean
    BeerService mockBeerService;

    @MockBean
    BeerMapper mockBeerMapper;

    @MockBean
    AuthenticationHelper mockAuthenticationHelper;

    @Autowired
    MockMvc mockMvc;

    @Test
    public void getBeerById_Should_Return_StatusOk_When_BeerExists() throws Exception {

        Beer mockBeer = createMockBeer();
        Mockito.when(mockBeerService.getBeerById(Mockito.anyInt()))
                .thenReturn(mockBeer);

        mockMvc.perform(MockMvcRequestBuilders.get("/api/beers/{id}", 1))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value(mockBeer.getName()));
    }

    @Test
    public void getBeerById_Should_Throw_StatusNotFound_When_BeerDoesNotExist() throws Exception {
        Beer mockBeer = createMockBeer();
        Mockito.when(mockBeerService.getBeerById(Mockito.anyInt()))
                .thenThrow(new EntityNotFoundException("Beer", mockBeer.getId()));

        mockMvc.perform(MockMvcRequestBuilders.get("/api/beers/{id}", mockBeer.getId()))
                .andExpect(MockMvcResultMatchers.status().isNotFound());
    }

    @Test
    public void getBeerByName_Should_Return_StatusOk_When_BeerExists() throws Exception {

        Beer mockBeer = createMockBeer();
        Mockito.when(mockBeerService.getBeerByName(Mockito.anyString()))
                .thenReturn(mockBeer);

        mockMvc.perform(MockMvcRequestBuilders.get("/api/beers/search")
                        .param("name", mockBeer.getName())
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value(mockBeer.getName()));
    }

    @Test
    public void getBeerByName_Should_Throw_StatusNotFound_When_BeerDoesNotExist() throws Exception {
        Beer mockBeer = createMockBeer();
        Mockito.when(mockBeerService.getBeerByName(Mockito.anyString()))
                .thenThrow(new EntityNotFoundException("Beer", mockBeer.getId()));

        mockMvc.perform(MockMvcRequestBuilders.get("/api/beers/search")
                        .param("name", mockBeer.getName())
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isNotFound());
    }

    @Test
    public void createBeer_Should_Return_StatusOk_When_CorrectRequest() throws Exception {
        Beer mockBeer = createMockBeer();
        User mockUser = createMockUser();

        Mockito.when(mockAuthenticationHelper.tryGetUser(Mockito.any()))
                .thenReturn(mockUser);

        Mockito.when(mockBeerMapper.dtoToBeer(Mockito.any()))
                .thenReturn(mockBeer);

        // Act, Assert

        String body = toJson(createMockBeerDto());

        mockMvc.perform(MockMvcRequestBuilders.post("/api/beers")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(body))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void createBeer_Should_ReturnStatusUnauthorized_When_AuthorizationIsMissing() throws Exception {

        Mockito.when(mockAuthenticationHelper.tryGetUser(Mockito.any()))
                .thenThrow(new ResponseStatusException(HttpStatus.UNAUTHORIZED, null));

        // Act, Assert

        String body = toJson(createMockBeerDto());

        mockMvc.perform(MockMvcRequestBuilders.post("/api/beers")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(body))
                .andExpect(MockMvcResultMatchers.status().isUnauthorized());
    }

    @Test
    public void createBeer_Should_ReturnStatusBadRequest_When_BodyIsInvalid() throws Exception {
        BeerDto mockBeerDto = createMockBeerDto();
        mockBeerDto.setName(null);

        // Act, Assert

        String body = toJson(mockBeerDto);

        mockMvc.perform(MockMvcRequestBuilders.post("/api/beers")
                .contentType(MediaType.APPLICATION_JSON)
                .content(body))
                .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }

    @Test
    public void create_Should_ReturnStatusNotFound_When_StyleDoesNotExist() throws Exception {
        User mockUser = createMockUser();

        Mockito.when(mockAuthenticationHelper.tryGetUser(Mockito.any()))
                .thenReturn(mockUser);

        Mockito.when(mockBeerMapper.dtoToBeer(Mockito.any()))
                .thenThrow(EntityNotFoundException.class);

        // Act, Assert
        String body = toJson(createMockBeerDto());

        mockMvc.perform(MockMvcRequestBuilders.post("/api/beers")
                .contentType(MediaType.APPLICATION_JSON)
                .content(body))
                .andExpect(MockMvcResultMatchers.status().isNotFound());
    }
}
