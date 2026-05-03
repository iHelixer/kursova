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
    private String portraitUrl; // Фото пілота для віджету Top Driver

    // Зв'язок: багато пілотів можуть належати до однієї команди
    @ManyToOne
    @JoinColumn(name = "team_id")
    private Team team;
}
