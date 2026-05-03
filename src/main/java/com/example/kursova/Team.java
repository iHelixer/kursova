package com.example.kursova;

import jakarta.persistence.*;

@Entity
@Table(name = "teams")
public class Team {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String principal; // Керівник команди
    private String carImage; // Фото боліда для віджету Top Team
    private int championshipPoints;
}