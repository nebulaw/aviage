package com.example.aviage.controllers;

import com.example.aviage.dtos.FlightDto;
import com.example.aviage.entities.Flight;
import com.example.aviage.entities.Ticket;
import com.example.aviage.services.IFlightService;
import jakarta.websocket.server.PathParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;


@RequestMapping("/flight")
@RestController
public class FlightController {

    private final IFlightService flightService;

    @Autowired
    public FlightController(IFlightService flightService) {
        this.flightService = flightService;
    }

    @PostMapping("/add")
    public Flight addFlight(@RequestBody FlightDto flightDto) {
        return flightService.addFlight(flightDto);
    }

    @GetMapping
    public List<Flight> getAllFlightsByCities(
            @RequestParam(value = "sourceCity", required = false) String sourceCity,
            @RequestParam(value = "destinationCity", required = false) String destinationCity
    ) {
        return flightService.getAllFlightsByCities(sourceCity, destinationCity);
    }

    @GetMapping("/{id}")
    public Optional<Flight> getFlightById(@PathVariable Long id) {
        return flightService.getFlightById(id);
    }

    @PostMapping("/{flightId}/buy-ticket")
    public Ticket buyTicket(
            @PathVariable Long flightId,
            @RequestParam(value = "personId") Long personId
    ) {
        return flightService.buyTicket(flightId, personId);
    }


    @GetMapping("/month-statistics")
    public Map<String, Integer> getMonthStatistics(
            @RequestParam(value = "date")
            @DateTimeFormat(pattern = "yyyy-MM") Date date
    ) {
        return flightService.getMonthStatistics(date);
    }

}
