package com.example.kursova;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "races")
public class Race {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String grandPrixName;
    private String circuitName;
    private LocalDate startDate;
    private LocalDate endDate;
    private String trackImageUrl;
}
