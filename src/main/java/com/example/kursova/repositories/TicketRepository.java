package com.example.kursova.repositories;

import com.example.kursova.Ticket;
import com.example.kursova.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TicketRepository extends JpaRepository<Ticket, Long> {
    List<Ticket> findByUserOrderByIdDesc(User user);
}