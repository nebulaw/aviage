package com.example.aviage.dtos;

import lombok.*;

import java.util.Date;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class FlightDto {
    private Long id;
    private Integer soldTickets;
    private Integer totalTickets;
    private Date flightDate;
    private Long sourceCity;
    private Long destinationCity;
}
