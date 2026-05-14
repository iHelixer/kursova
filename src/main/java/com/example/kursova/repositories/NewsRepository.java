package com.example.kursova.repositories;

import com.example.kursova.News;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NewsRepository extends JpaRepository<News, Long> {
    List<News> findTop3ByOrderByPublishedDateDesc();
}
