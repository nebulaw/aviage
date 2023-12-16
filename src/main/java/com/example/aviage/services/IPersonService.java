package com.example.aviage.services;

import com.example.aviage.dtos.PersonDto;
import com.example.aviage.entities.Person;


public interface IPersonService {

    Person addPerson(PersonDto person);

}
