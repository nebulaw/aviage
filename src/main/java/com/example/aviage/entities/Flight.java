package com.example.aviage.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Getter
@Setter
@ToString
@EqualsAndHashCode(callSuper = true)
@RequiredArgsConstructor
@Entity
@Table(name = "FLIGHTS")
public class Flight extends AppEntity {

    @Id
    @SequenceGenerator(name = "flightGen", sequenceName = "FLIGHT_SEQ", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "flightGen")
    @Column(name = "ID", updatable = false)
    private Long id;

    @Column(name = "SOLD_TICKETS", nullable = false)
    private Integer soldTickets;

    @Column(name = "TOTAL_TICKETS", nullable = false)
    private Integer totalTickets;

    @Temporal(TemporalType.DATE)
    @Column(name = "FLIGHT_DATE")
    private Date flightDate;

    @ManyToOne
    private City sourceCity;

    @ManyToOne
    private City destinationCity;

    public boolean buyTicket() {
        if (soldTickets.equals(totalTickets)) {
            return false;
        }
        soldTickets++;
        return true;
    }

}


