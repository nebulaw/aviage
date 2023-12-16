package com.example.aviage.entities;

import jakarta.persistence.*;
import lombok.*;


@Getter
@Setter
@ToString
@EqualsAndHashCode(callSuper = true)
@RequiredArgsConstructor
@Entity
@Table(name = "CITIES")
public class City extends AppEntity {

    @Id
    @SequenceGenerator(name = "cityGen", sequenceName = "CITY_SEQ", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "cityGen")
    @Column(name = "ID", updatable = false)
    private Long id;

    @Column(name = "CITY_NAME", nullable = false)
    private String name;

}
