package com.example.kursova.repositories;

import com.example.kursova.News;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

// Репозиторій для роботи з новинами
@Repository
public interface NewsRepository extends JpaRepository<News, Long> {
    // Spring Data автоматично згенерує SQL-запит для пошуку останніх новин за датою
    List<News> findTop3ByOrderByPublishedDateDesc();
}
