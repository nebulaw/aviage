package com.example.aviage.services.impl;

import com.example.aviage.dtos.CityDto;
import com.example.aviage.entities.City;
import com.example.aviage.entities.RecordState;
import com.example.aviage.repositories.CityRepository;
import com.example.aviage.services.ICityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CityServiceImpl implements ICityService {

    private final CityRepository cityRepository;

    @Autowired
    public CityServiceImpl(CityRepository cityRepository) {
        this.cityRepository = cityRepository;
    }


    @Override
    public City addCity(CityDto cityDto) {
        City city = new City();
        System.out.println("Create date" + city.getCreateDate());
        if (cityDto != null && cityDto.getName() != null) {
            city.setName(cityDto.getName());
            return cityRepository.save(city);
        } else {
            return null;
        }
    }

    @Override
    public Optional<City> getById(Long id) {
        return cityRepository.findByIdAndRecordState(id, RecordState.ACTIVE);
    }

    @Override
    public Optional<City> getByName(String name) {
        return cityRepository.findByNameAndRecordState(name, RecordState.ACTIVE);
    }

    @Override
    public List<City> getAll() {
        return cityRepository.findAllByRecordState(RecordState.ACTIVE);
    }
}
