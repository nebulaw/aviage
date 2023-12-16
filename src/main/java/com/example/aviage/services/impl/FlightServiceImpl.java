package com.example.aviage.services.impl;

import com.example.aviage.dtos.FlightDto;
import com.example.aviage.entities.*;
import com.example.aviage.repositories.FlightRepository;
import com.example.aviage.repositories.PersonRepository;
import com.example.aviage.repositories.TicketRepository;
import com.example.aviage.services.ICityService;
import com.example.aviage.services.IFlightService;
import com.example.aviage.specifications.FlightSpecification;
import com.example.aviage.specifications.TicketSpecification;
import com.fasterxml.jackson.datatype.jsr310.deser.key.LocalDateTimeKeyDeserializer;
import jakarta.persistence.criteria.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.util.StreamUtils;
import org.springframework.util.StringUtils;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class FlightServiceImpl implements IFlightService {

    private final ICityService cityService;
    private final FlightRepository flightRepository;
    private final TicketRepository ticketRepository;
    private final PersonRepository personRepository;

    @Autowired
    public FlightServiceImpl(
            ICityService cityService,
            FlightRepository flightRepository,
            TicketRepository ticketRepository,
            PersonRepository personRepository
    ) {
        this.cityService = cityService;
        this.flightRepository = flightRepository;
        this.ticketRepository = ticketRepository;
        this.personRepository = personRepository;
    }

    @Override
    public Flight addFlight(FlightDto flightDto) {
        if (flightDto == null) {
            return null;
        }

        if (flightDto.getSoldTickets() == null ||
                flightDto.getTotalTickets() == null ||
                flightDto.getSourceCity() == null ||
                flightDto.getDestinationCity() == null ||
                flightDto.getFlightDate() == null) {
            return null;
        }

        Flight flight = new Flight();

        flight.setSoldTickets(flightDto.getSoldTickets());
        flight.setTotalTickets(flightDto.getTotalTickets());
        flight.setFlightDate(flightDto.getFlightDate());

        Optional<City> optional = cityService.getById(flightDto.getSourceCity());
        if (optional.isEmpty()) {
            return null;
        }
        City sourceCity = optional.get();
        flight.setSourceCity(sourceCity);

        optional = cityService.getById(flightDto.getDestinationCity());
        if (optional.isEmpty()) {
            return null;
        }
        City destinationCity = optional.get();
        flight.setDestinationCity(destinationCity);

        return flightRepository.save(flight);
    }

    @Override
    public Optional<Flight> getFlightById(Long id) {
        return id != null ? flightRepository.findByIdAndRecordState(id, RecordState.ACTIVE) : null;
    }

    @Override
    public List<Flight> getAllFlightsByCities(String sourceCity, String destinationCity) {
        if (!StringUtils.hasText(sourceCity) || !StringUtils.hasText(destinationCity)) {
            return flightRepository.findAll();
        }
        City sCity = cityService.getByName(sourceCity).orElse(null);
        City dCity = cityService.getByName(destinationCity).orElse(null);

        if (sCity != null && dCity != null) {
            Specification<Flight> specification = Specification.where(
                    FlightSpecification.sourceAndDestinationCityEquals(sCity, dCity));
            return flightRepository.findAll(specification);
        } else {
            return flightRepository.findAll();
        }
    }

    @Override
    public Ticket buyTicket(Long flightId, Long personId) {
        if (flightId == null || personId == null) {
            System.out.println("null flight id or person id");
            return null;
        }

        Flight flight = flightRepository.findByIdAndRecordState(flightId, RecordState.ACTIVE).orElse(null);
        if (flight == null) {
            System.out.println("cannot find flight");
            return null;
        }

        flight.buyTicket();
        flightRepository.save(flight);

        Person person = personRepository.findByIdAndRecordState(personId, RecordState.ACTIVE).orElse(null);
        if (person == null) {
            System.out.println("person not found at " + personId);
            return null;
        }

        Ticket ticket = new Ticket(-1L, flight, person);
        return ticketRepository.save(ticket);
    }

    // left join, better solution?
    @Override
    public List<Ticket> getAllTicketsByCities(String sourceCity, String destinationCity) {
        Specification<Ticket> specification = Specification.where(TicketSpecification.idNotNull());
        specification.and((root, query, builder) -> {
            Join<Ticket, Flight> join = root.join("flight", JoinType.LEFT);
            Predicate destPred = builder.equal(join.get("destinationCity"), destinationCity);
            Predicate srcPred = builder.equal(join.get("sourceCity"), sourceCity);
            return builder.and(srcPred, destPred);
        });

        return ticketRepository.findAll(specification);
    }

    @Override
    public Map<String, Integer> getMonthStatistics(Date date) {
        Specification<Flight> specification = Specification.where(FlightSpecification.flightMonthEquals(date));

        List<Flight> flights = flightRepository.findAll(specification);
        long ticketCount = flights.stream()
                .flatMap(flight -> getAllTicketsByCities(flight.getSourceCity().getName(),
                        flight.getDestinationCity().getName()).stream())
                .count();

        Map<String, Integer> map = new HashMap<>();
        map.put("Flights", flights.size());
        map.put("Tickets", (int)ticketCount);
        return map;
    }

    @Override
    public List<Ticket> getTicketsByPerson(String fullName) {
        Specification<Person> specification = Specification.where((root, query, builder) ->
                builder.equal(root.get("fullName"), fullName));

        return personRepository.findAll(specification).stream()
                .flatMap(person -> ticketRepository.findAll((r, q, b) ->
                        b.equal(r.get("owner"), person)).stream())
                .toList();
    }

}
