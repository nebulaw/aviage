package com.example.aviage.services;

import com.example.aviage.dtos.FlightDto;
import com.example.aviage.entities.Flight;
import com.example.aviage.entities.Ticket;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface IFlightService {

    Flight addFlight(FlightDto flightDto);

    Optional<Flight> getFlightById(Long id);

    List<Flight> getAllFlightsByCities(String sourceCity, String destinationCity);

    Ticket buyTicket(Long flightId, Long personId);

    List<Ticket> getAllTicketsByCities(String sourceCity, String destinationCity);

    Map<String, Integer> getMonthStatistics(Date date);

    List<Ticket> getTicketsByPerson(String fullName);

}
