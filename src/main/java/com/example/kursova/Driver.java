package com.example.kursova;

import jakarta.persistence.*;

@Entity
@Table(name = "drivers")
public class Driver {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private int racingNumber;
    private int seasonPoints;
    private String portraitUrl;

    @ManyToOne
    @JoinColumn(name = "team_id")
    private Team team;
}
