package com.example.aviage.controllers;

import com.example.aviage.dtos.CityDto;
import com.example.aviage.entities.City;
import com.example.aviage.services.ICityService;
import jakarta.websocket.server.PathParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class CityController {

    private final ICityService cityService;

    @Autowired
    public CityController(ICityService cityService) {
        this.cityService = cityService;
    }


    @PostMapping("/city/add")
    public City addCity(@RequestBody CityDto cityDto) {
        return cityService.addCity(cityDto);
    }

    @GetMapping("/city")
    public Object getCity(
            @PathParam(value = "id") Long id,
            @PathParam(value = "name") String name
    ) {
        if (id != null) {
            return cityService.getById(id);
        } else if (name != null) {
            return cityService.getByName(name);
        } else {
            return cityService.getAll();
        }
    }

}
