package com.example.kursova.repositories;

import com.example.kursova.Team;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
// Репозиторій для роботи з командами
@Repository
public interface TeamRepository extends JpaRepository<Team, Long> {
    // Знайти найкращу команду в Кубку конструкторів
    Team findTopByOrderByChampionshipPointsDesc();
}
