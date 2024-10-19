package com.telerikacademy.beertag.services;

import com.telerikacademy.beertag.exceptions.AuthorizationException;
import com.telerikacademy.beertag.exceptions.EntityDuplicateException;
import com.telerikacademy.beertag.exceptions.EntityNotFoundException;
import com.telerikacademy.beertag.models.Beer;
import com.telerikacademy.beertag.models.FilterOptions;
import com.telerikacademy.beertag.models.Style;
import com.telerikacademy.beertag.models.User;
import com.telerikacademy.beertag.repositories.BeerRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static com.telerikacademy.beertag.Helper.*;

@ExtendWith(MockitoExtension.class)
public class BeerServicesImplTests {

    @Mock
    BeerRepository mockBeerRepository;

    @InjectMocks
    BeerServiceImpl beerService;

    @Test
    void getAllBeers_Should_CallRepository() {
        // Arrange
        FilterOptions mockFilterOptions = createMockFilterOptions();
        Mockito.when(mockBeerRepository.getAllBeers(mockFilterOptions))
                .thenReturn(null);

        // Act
        beerService.getAllBeers(mockFilterOptions);

        // Assert
        Mockito.verify(mockBeerRepository, Mockito.times(1))
                .getAllBeers(mockFilterOptions);
    }

    @Test
    public void getAllBeers_Should_ReturnBeers_When_BeersWithThisParamsExist() {
        Style mockStyle = createMockStyle();

        Beer beer = new Beer(2, "Nikolay Pivo", 4.6, mockStyle);

        List<Beer> mockBeers = new ArrayList<>();
        mockBeers.add(beer);

        FilterOptions filterOptions = createMockFilterOptions();
        Mockito.when(mockBeerRepository.getAllBeers(filterOptions))
                .thenReturn(mockBeers);


        List<Beer> beers = beerService.getAllBeers(filterOptions);

        Assertions.assertEquals(1, beers.size());
    }

    @Test
    public void getBeerById_Should_ReturnBeer_When_BeerExists() {
        Style mockStyle = createMockStyle();
        Mockito.when(mockBeerRepository.getBeerById(2))
                .thenReturn(new Beer(2, "Nikolay Pivo", 4.6, mockStyle));

        Beer beer = beerService.getBeerById(2);

        Assertions.assertEquals(2, beer.getId());
        Assertions.assertEquals("Nikolay Pivo", beer.getName());
        Assertions.assertEquals(4.6, beer.getAbv());
        Assertions.assertEquals(mockStyle, beer.getStyle());
    }

    @Test
    public void getBeerByName_Should_ReturnBeer_When_BeerExists() {
        var mockStyle = createMockStyle();
        Mockito.when(mockBeerRepository.getBeerByName("Nikolay Pivo"))
                .thenReturn(new Beer(2, "Nikolay Pivo", 4.6, mockStyle));

        Beer beer = beerService.getBeerByName("Nikolay Pivo");

        Assertions.assertEquals(2, beer.getId());
        Assertions.assertEquals("Nikolay Pivo", beer.getName());
        Assertions.assertEquals(4.6, beer.getAbv());
        Assertions.assertEquals(mockStyle, beer.getStyle());
    }


    @Test
    public void create_Should_Throw_When_BeerWithThisNameExists() {

        var mockBeer = createMockBeer();
        var mockUser = createMockUser();

        Mockito.when(mockBeerRepository.getBeerByName(mockBeer.getName()))
                .thenReturn(mockBeer);

        Assertions.assertThrows(EntityDuplicateException.class,
                () -> beerService.createNewBeer(mockBeer, mockUser));
    }

    @Test
    public void create_Should_CreateBeer_When_BeerWithThisNameDoesNotExist() {
        var mockStyle = createMockStyle();
        var mockBeer = new Beer(3, "Pivo 2", 4.3, mockStyle);
        var mockUser = createMockUser();


        Mockito.when(mockBeerRepository.getBeerByName(mockBeer.getName()))
                .thenThrow(new EntityNotFoundException("Beer", "name", mockBeer.getName()));

        beerService.createNewBeer(mockBeer, mockUser);

        Mockito.verify(mockBeerRepository, Mockito.times(1))
                .createNewBeer(Mockito.any(Beer.class));
    }

    @Test
    public void update_Should_Throw_When_UserNotCreatorOrAdmin() {
        Beer mockBeer = createMockBeer();
        var mockUser = createMockUser();
        mockUser.setId(5);
        mockUser.setUsername("InvalidUsername");
        mockBeer.setCreatedBy(mockUser);

        Mockito.when(mockBeerRepository.getBeerById(Mockito.anyInt()))
                .thenReturn(mockBeer);

        Assertions.assertThrows(AuthorizationException.class,
                () -> beerService.updateBeer(mockBeer, createMockUser()));
    }

    @Test
    public void update_Should_ThrowException_When_UserIsNotCreatorOrAdmin() {
        // Arrange
        Beer mockBeer = createMockBeer();

        Mockito.when(mockBeerRepository.getBeerById(mockBeer.getId()))
                .thenReturn(mockBeer);

        // Act, Assert
        Assertions.assertThrows(
                AuthorizationException.class,
                () -> beerService.updateBeer(mockBeer, Mockito.mock(User.class)));
    }

    @Test
    public void update_Should_Throw_When_BeerWithSameNameExists() {
        Beer mockBeer = createMockBeer();
        Beer anotherMockBeer = createMockBeer();
        anotherMockBeer.setId(2);

        Mockito.when(mockBeerRepository.getBeerById(Mockito.anyInt()))
                .thenReturn(mockBeer);

        Mockito.when(mockBeerRepository.getBeerByName(Mockito.anyString()))
                .thenReturn(anotherMockBeer);

        Assertions.assertThrows(EntityDuplicateException.class,
                () -> beerService.updateBeer(mockBeer, createMockUser()));
    }

    @Test
    public void update_Should_ThrowException_When_BeerNameIsTaken() {
        // Arrange
        Beer mockBeer = createMockBeer();
        User mockUserCreator = mockBeer.getCreatedBy();

        Mockito.when(mockBeerRepository.getBeerById(Mockito.anyInt()))
                .thenReturn(mockBeer);

        Beer mockExistingBeerWithSameName = createMockBeer();
        mockExistingBeerWithSameName.setId(2);

        Mockito.when(mockBeerRepository.getBeerByName(mockBeer.getName()))
                .thenReturn(mockExistingBeerWithSameName);

        // Act, Assert
        Assertions.assertThrows(
                EntityDuplicateException.class,
                () -> beerService.updateBeer(mockBeer, mockUserCreator));
    }

    @Test
    public void update_Should_UpdateBeer_When_UserIsCreator() {
        var mockBeer = createMockBeer();
        mockBeer.setAbv(4.5);
        var anotherMockBeer = createMockBeer();
        anotherMockBeer.setAbv(7.4);

        Mockito.when(mockBeerRepository.getBeerById(Mockito.anyInt()))
                .thenReturn(mockBeer);

        Mockito.when(mockBeerRepository.getBeerByName(Mockito.anyString()))
                .thenReturn(anotherMockBeer);

        beerService.updateBeer(mockBeer, createMockUser());

        Mockito.verify(mockBeerRepository, Mockito.times(1))
                .updateBeer(Mockito.any(Beer.class));
    }

    @Test
    void update_Should_CallRepository_When_UserIsCreator() {
        // Arrange
        Beer mockBeer = createMockBeer();
        User mockUserCreator = mockBeer.getCreatedBy();

        Mockito.when(mockBeerRepository.getBeerById(Mockito.anyInt()))
                .thenReturn(mockBeer);

        Mockito.when(mockBeerRepository.getBeerByName(Mockito.anyString()))
                .thenThrow(EntityNotFoundException.class);

        // Act
        beerService.updateBeer(mockBeer, mockUserCreator);

        // Assert
        Mockito.verify(mockBeerRepository, Mockito.times(1))
                .updateBeer(mockBeer);
    }


    @Test
    public void update_Should_UpdateBeer_When_UserIsAdmin() {
        var mockBeer = createMockBeer();
        mockBeer.setAbv(4.5);
        var anotherMockBeer = createMockBeer();
        anotherMockBeer.setAbv(7.4);

        Mockito.when(mockBeerRepository.getBeerById(Mockito.anyInt()))
                .thenReturn(mockBeer);

        Mockito.when(mockBeerRepository.getBeerByName(Mockito.anyString()))
                .thenReturn(anotherMockBeer);

        beerService.updateBeer(mockBeer, createMockAdmin());

        Mockito.verify(mockBeerRepository, Mockito.times(1))
                .updateBeer(Mockito.any(Beer.class));
    }

    @Test
    void update_Should_CallRepository_When_UserIsAdmin() {
        // Arrange
        User mockUserAdmin = createMockAdmin();
        Beer mockBeer = createMockBeer();

        Mockito.when(mockBeerRepository.getBeerById(Mockito.anyInt()))
                .thenReturn(mockBeer);

        Mockito.when(mockBeerRepository.getBeerByName(Mockito.anyString()))
                .thenThrow(EntityNotFoundException.class);

        // Act
        beerService.updateBeer(mockBeer, mockUserAdmin);

        // Assert
        Mockito.verify(mockBeerRepository, Mockito.times(1))
                .updateBeer(mockBeer);
    }

    @Test
    public void update_Should_CallRepository_When_UpdatingExistingBeer() {
        // Arrange
        Beer mockBeer = createMockBeer();
        User mockUserCreator = mockBeer.getCreatedBy();

        Mockito.when(mockBeerRepository.getBeerById(Mockito.anyInt()))
                .thenReturn(mockBeer);

        Mockito.when(mockBeerRepository.getBeerByName(Mockito.anyString()))
                .thenReturn(mockBeer);

        // Act
        beerService.updateBeer(mockBeer, mockUserCreator);

        // Assert
        Mockito.verify(mockBeerRepository, Mockito.times(1))
                .updateBeer(mockBeer);
    }

    @Test
    public void delete_Should_Throw_When_UserNotCreatorOrAdmin() {
        Beer mockBeer = createMockBeer();
        User mockUser = createMockUser();
        mockUser.setId(5);
        mockUser.setUsername("InvalidUsername");
        mockBeer.setCreatedBy(mockUser);

        Mockito.when(mockBeerRepository.getBeerById(Mockito.anyInt()))
                .thenReturn(mockBeer);

        Assertions.assertThrows(AuthorizationException.class,
                () -> beerService.deleteBeer(mockBeer.getId(), createMockUser()));
    }

    @Test
    void delete_Should_ThrowException_When_UserIsNotAdminOrCreator() {
        // Arrange
        Beer mockBeer = createMockBeer();

        Mockito.when(mockBeerRepository.getBeerById(Mockito.anyInt()))
                .thenReturn(mockBeer);

        User mockUser = Mockito.mock(User.class);

        // Act, Assert
        Assertions.assertThrows(
                AuthorizationException.class,
                () -> beerService.deleteBeer(1, mockUser));
    }

    @Test
    public void delete_Should_DeleteBeer_When_UserIsCreator() {
        var mockBeer = createMockBeer();

        Mockito.when(mockBeerRepository.getBeerById(Mockito.anyInt()))
                .thenReturn(mockBeer);

        beerService.deleteBeer(mockBeer.getId(), createMockUser());

        Mockito.verify(mockBeerRepository, Mockito.times(1))
                .deleteBeer(Mockito.anyInt());
    }

    @Test
    void delete_Should_CallRepository_When_UserIsCreator() {
        // Arrange
        Beer mockBeer = createMockBeer();
        User mockUserCreator = mockBeer.getCreatedBy();

        Mockito.when(mockBeerRepository.getBeerById(Mockito.anyInt()))
                .thenReturn(mockBeer);

        // Act
        beerService.deleteBeer(1, mockUserCreator);

        // Assert
        Mockito.verify(mockBeerRepository, Mockito.times(1))
                .deleteBeer(1);
    }


    @Test
    public void delete_Should_DeleteBeer_When_UserIsAdmin() {
        var mockBeer = createMockBeer();

        Mockito.when(mockBeerRepository.getBeerById(Mockito.anyInt()))
                .thenReturn(mockBeer);

        beerService.deleteBeer(mockBeer.getId(), createMockAdmin());

        Mockito.verify(mockBeerRepository, Mockito.times(1))
                .deleteBeer(Mockito.anyInt());
    }

    @Test
    void delete_Should_CallRepository_When_UserIsAdmin() {
        // Arrange
        User mockUserAdmin = createMockAdmin();
        Beer mockBeer = createMockBeer();

        Mockito.when(mockBeerRepository.getBeerById(Mockito.anyInt()))
                .thenReturn(mockBeer);

        // Act
        beerService.deleteBeer(1, mockUserAdmin);

        // Assert
        Mockito.verify(mockBeerRepository, Mockito.times(1))
                .deleteBeer(1);
    }

}
