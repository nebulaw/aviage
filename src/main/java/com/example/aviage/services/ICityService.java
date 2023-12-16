package com.example.aviage.services;

import com.example.aviage.dtos.CityDto;
import com.example.aviage.entities.City;

import java.util.List;
import java.util.Optional;

public interface ICityService {

    City addCity(CityDto cityDto);

    Optional<City> getById(Long id);

    Optional<City> getByName(String name);

    List<City> getAll();

}
