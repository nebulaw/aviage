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
@Table(name = "PEOPLE")
public class Person extends AppEntity {

    @Id
    @SequenceGenerator(name = "personGen", sequenceName = "PERSON_SEQ", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "personGen")
    @Column(name = "ID", updatable = false)
    private Long id;

    @Column(name = "FULL_NAME", nullable = false)
    private String fullName;

}
