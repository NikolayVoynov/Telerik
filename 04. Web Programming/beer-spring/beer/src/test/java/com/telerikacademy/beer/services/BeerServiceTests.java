package com.telerikacademy.beer.services;

import com.telerikacademy.beer.exceptions.DuplicateEntityException;
import com.telerikacademy.beer.exceptions.EntityNotFoundException;
import com.telerikacademy.beer.exceptions.UnauthorizedOperationException;
import com.telerikacademy.beer.models.Beer;
import com.telerikacademy.beer.repositories.BeerRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static com.telerikacademy.beer.Helpers.createMockBeer;
import static com.telerikacademy.beer.Helpers.createMockUser;

@ExtendWith(MockitoExtension.class)
public class BeerServiceTests {

    @Mock
    BeerRepository mockBeerRepository;

    @InjectMocks
    BeerServiceImpl beerService;

    @Test
    public void getBeerById_Should_ReturnBeer_When_MatchExists() {

        // Arrange
        Mockito.when(mockBeerRepository.getBeerById(2))
                .thenReturn(new Beer(2, "Nikolay Pivo", 7.3));

        // Act
        Beer beer = beerService.getBeerById(2);

        // Assert

        Assertions.assertEquals(2, beer.getId());
        Assertions.assertEquals("Nikolay Pivo", beer.getName());
        Assertions.assertEquals(7.3, beer.getAbv());
    }

    @Test
    public void create_Should_Throw_When_BeerWithSameNameExists() {
        // Arrange
        var mockBeer = createMockBeer();

        Mockito.when(mockBeerRepository.getBeerByName(mockBeer.getName()))
                .thenReturn(mockBeer);

        // Act, Assert

        Assertions.assertThrows(DuplicateEntityException.class, () -> beerService.createBeer(mockBeer));
    }

    @Test
    public void create_Should_CallRepository_When_BeerWithSameNameNotExist() {

        // Arrange
        var mockBeer = new Beer(3, "Pivo 2", 4.3);

        Mockito.when(mockBeerRepository.getBeerByName(mockBeer.getName()))
                .thenThrow(new EntityNotFoundException("Beer", "name", mockBeer.getName()));

//        beerService.createBeer(mockBeer);

        // Act, Assert

        Mockito.verify(mockBeerRepository, Mockito.times(1))
                .createBeer(Mockito.any(Beer.class));
    }

    @Test
    public void update_Should_Throw_When_UserIsNotCreatorOrAdmin() {
        var mockBeer = createMockBeer();
        var mockUser = createMockUser();

//        mockUser.setId(5);
        mockUser.setUsername("MockUser2");
        mockBeer.setCreatedBy(mockUser);

        Mockito.when(mockBeerRepository.getBeerById(Mockito.anyInt()))
                .thenReturn(mockBeer);

        Assertions.assertThrows(UnauthorizedOperationException.class,
                () -> beerService.updateBeer(mockBeer, createMockUser()));

    }

    @Test
    public void update_Should_Throw_When_BeerNameIsTaken() {
        var mockBeer = createMockBeer();
        var anotherMockBeer = createMockBeer();
        anotherMockBeer.setId(2);

        Mockito.when(mockBeerRepository.getBeerById(Mockito.anyInt()))
                .thenReturn(mockBeer);

        Mockito.when(mockBeerRepository.getBeerByName(Mockito.anyString()))
                .thenReturn(anotherMockBeer);

        Assertions.assertThrows(DuplicateEntityException.class,
                () -> beerService.updateBeer(mockBeer, createMockUser()));

    }

}
