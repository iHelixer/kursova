package com.example.kursova.repositories;

import com.example.kursova.Team;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface TeamRepository extends JpaRepository<Team, Long> {
    Team findTopByOrderByChampionshipPointsDesc();
}
