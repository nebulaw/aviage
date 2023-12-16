package com.example.aviage.specifications;

import com.example.aviage.entities.Ticket;
import org.springframework.data.jpa.domain.Specification;

public class TicketSpecification {

    public static Specification<Ticket> idNotNull() {
        return (root, query, builder) -> builder.isNotNull(root.get("ID"));
    }

    public static Specification<Ticket> flightEquals(Long id) {
        return (root, query, builder) -> builder.equal(root.get("flight"), id);
    }

}
