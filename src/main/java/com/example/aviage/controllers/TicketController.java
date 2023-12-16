package com.example.aviage.controllers;

import com.example.aviage.entities.Ticket;
import com.example.aviage.services.IFlightService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class TicketController {

    private final IFlightService flightService;

    @Autowired
    public TicketController(IFlightService flightService) {
        this.flightService = flightService;
    }

    @GetMapping("/tickets")
    public List<Ticket> getAllTicketsByCities(
            @RequestParam(value = "sourceCity") String sourceCity,
            @RequestParam(value = "destinationCity") String destinationCity
    ) {
        return flightService.getAllTicketsByCities(sourceCity, destinationCity);
    }

}
