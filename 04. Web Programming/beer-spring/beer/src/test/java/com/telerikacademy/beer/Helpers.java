package com.telerikacademy.beer;

import com.telerikacademy.beer.models.Beer;
import com.telerikacademy.beer.models.Style;
import com.telerikacademy.beer.models.User;

public class Helpers {

    public static User createMockUser() {
        var mockUser = new User();
        mockUser.setId(1);
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
}
