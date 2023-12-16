package com.example.aviage.controllers;

import com.example.aviage.dtos.PersonDto;
import com.example.aviage.entities.Person;
import com.example.aviage.entities.Ticket;
import com.example.aviage.services.IFlightService;
import com.example.aviage.services.IPersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/person")
public class PersonController {

    private final IPersonService personService;
    private final IFlightService flightService;

    @Autowired
    public PersonController(IPersonService personService, IFlightService flightService) {
        this.personService = personService;
        this.flightService = flightService;
    }

    @PostMapping("/add")
    public Person addPerson(@RequestBody PersonDto personDto) {
        return personService.addPerson(personDto);
    }

    @GetMapping("/{fullName}/tickets")
    public List<Ticket> getTicketsByPerson(@PathVariable String fullName) {
        return flightService.getTicketsByPerson(fullName);
    }

}
