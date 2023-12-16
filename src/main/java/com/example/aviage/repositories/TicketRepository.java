package com.example.aviage.repositories;

import com.example.aviage.entities.RecordState;
import com.example.aviage.entities.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.Optional;

public interface TicketRepository extends JpaRepository<Ticket, Long>, JpaSpecificationExecutor<Ticket> {

    Optional<Ticket> findByIdAndRecordState(Long id, RecordState recordState);

}
