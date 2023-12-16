package com.example.aviage.repositories;

import com.example.aviage.entities.Person;
import com.example.aviage.entities.RecordState;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.Optional;

public interface PersonRepository extends JpaRepository<Person, Long>, JpaSpecificationExecutor<Person> {

    Optional<Person> findByIdAndRecordState(Long id, RecordState recordState);

}
