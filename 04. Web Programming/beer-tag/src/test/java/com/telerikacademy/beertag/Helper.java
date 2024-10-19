package com.telerikacademy.beertag;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.telerikacademy.beertag.models.*;
import org.springframework.http.HttpHeaders;

public class Helper {

    public static User createMockAdmin() {
        User mockUser = createMockUser();
        mockUser.setAdmin(true);

        return mockUser;
    }

    public static User createMockUser() {
        var mockUser = new User();
        mockUser.setId(1);
        mockUser.setUsername("MockUsername");
        mockUser.setPassword("MockPassword");
        mockUser.setFirstName("MockFirstName");
        mockUser.setLastName("MockLastName");
        mockUser.setEmail("mock@user.com");

        return mockUser;
    }

    public static Beer createMockBeer() {
        var mockBeer = new Beer();

        mockBeer.setId(1);
        mockBeer.setName("MockBeer");
        mockBeer.setCreatedBy(createMockUser());
        mockBeer.setStyle(createMockStyle());

        return mockBeer;
    }

    public static Style createMockStyle() {
        var mockStyle = new Style();
        mockStyle.setId(1);
        mockStyle.setName("MockStyle");

        return mockStyle;
    }

    public static BeerDto createMockBeerDto() {
        BeerDto mockBeerDto = new BeerDto();
        mockBeerDto.setStyleId(1);
        mockBeerDto.setName("MockBeer");
        mockBeerDto.setAbv(4.5);

        return mockBeerDto;
    }

    public static String toJson(final Object object) {
        try {
            return new ObjectMapper().writeValueAsString(object);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static FilterOptions createMockFilterOptions() {
        FilterOptions mockFilterOptions =
                new FilterOptions(
                        "Nikolay Pivo",
                        4.3,
                        5.7,
                        1,
                        "sort",
                        "order");

        return mockFilterOptions;
    }

    public static HttpHeaders createMockHttpHeaders(){
        HttpHeaders mockHttpHeaders = new HttpHeaders();
        mockHttpHeaders.add("username","MockUsername");
        mockHttpHeaders.add("password","MockPassword");

        return mockHttpHeaders;
    }

}
