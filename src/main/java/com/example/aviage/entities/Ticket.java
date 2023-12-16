package com.example.aviage.entities;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@ToString
@EqualsAndHashCode(callSuper = true)
@RequiredArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "TICKETS")
public class Ticket extends AppEntity {

    @Id
    @SequenceGenerator(name = "ticketGen", sequenceName = "TICKET_SEQ", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ticketGen")
    @Column(name = "ID", updatable = false)
    private Long id;

    @ManyToOne
    private Flight flight;

    @ManyToOne
    private Person owner;

}
