package com.example.kursova.repositories;

import com.example.kursova.Driver;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
// Репозиторій для роботи з пілотами
@Repository
public interface DriverRepository extends JpaRepository<Driver, Long> {
    // Знайти найкращого пілота (з найбільшою кількістю очок)
    Driver findTopByOrderBySeasonPointsDesc();
}
