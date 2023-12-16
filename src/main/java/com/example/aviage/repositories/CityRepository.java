package com.example.aviage.repositories;

import com.example.aviage.entities.City;
import com.example.aviage.entities.RecordState;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CityRepository extends JpaRepository<City, Long> {

    Optional<City> findByIdAndRecordState(Long id, RecordState recordState);

    List<City> findAllByRecordState(RecordState recordState);

    Optional<City> findByNameAndRecordState(String name, RecordState recordState);

}
