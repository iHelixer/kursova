package com.example.kursova.repositories;

import com.example.kursova.Race;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

// Репозиторій для календаря гонок
@Repository
public interface RaceRepository extends JpaRepository<Race, Long> {
    // Можна додати метод для пошуку наступної гонки від поточної дати
}
