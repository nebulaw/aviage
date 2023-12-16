package com.example.aviage.services.impl;

import com.example.aviage.dtos.PersonDto;
import com.example.aviage.entities.Person;
import com.example.aviage.repositories.PersonRepository;
import com.example.aviage.services.IPersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PersonServiceImpl implements IPersonService {

    private final PersonRepository personRepository;

    @Autowired
    public PersonServiceImpl(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    @Override
    public Person addPerson(PersonDto personDto) {
        return personRepository.save(new Person(-1L, personDto.getFullName()));
    }
}
