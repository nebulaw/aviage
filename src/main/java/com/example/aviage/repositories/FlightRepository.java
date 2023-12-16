package com.example.aviage.repositories;

import com.example.aviage.entities.Flight;
import com.example.aviage.entities.RecordState;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.Optional;

public interface FlightRepository extends JpaRepository<Flight, Long>, JpaSpecificationExecutor<Flight> {

    Optional<Flight> findByIdAndRecordState(Long flightId, RecordState recordState);

}
