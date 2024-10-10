package com.telerikacademy.beertag.helpers;

import com.telerikacademy.beertag.models.Beer;
import com.telerikacademy.beertag.models.BeerDto;
import com.telerikacademy.beertag.services.BeerService;
import com.telerikacademy.beertag.services.StyleService;
import com.telerikacademy.beertag.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class BeerMapper {

    private final StyleService styleService;
    private final BeerService beerService;

    @Autowired
    public BeerMapper(StyleService styleService, BeerService beerService) {
        this.styleService = styleService;
        this.beerService = beerService;
    }

    public Beer dtoToBeer(int id, BeerDto beerDto) {
        Beer beer = dtoToBeer(beerDto);
        beer.setId(id);
        beer.setCreatedBy(beerService.getBeerById(id).getCreatedBy());

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
