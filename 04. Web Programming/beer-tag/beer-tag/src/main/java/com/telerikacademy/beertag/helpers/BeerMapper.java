package com.telerikacademy.beertag.helpers;

import com.telerikacademy.beertag.models.Beer;
import com.telerikacademy.beertag.models.BeerDto;
import com.telerikacademy.beertag.services.StyleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class BeerMapper {

    private final StyleService styleService;

    @Autowired
    public BeerMapper(StyleService styleService) {
        this.styleService = styleService;
    }

    public Beer dtoToBeer(int id, BeerDto beerDto) {
        Beer beer = dtoToBeer(beerDto);
        beer.setId(id);

        return beer;
    }

    public Beer dtoToBeer(BeerDto beerDto) {
        Beer beer = new Beer();
        beer.setName(beerDto.getName());
        beer.setAbv(beerDto.getAbv());
        beer.setStyle(styleService.getStyleById(beerDto.getStyleId()));

        return beer;
    }

}
