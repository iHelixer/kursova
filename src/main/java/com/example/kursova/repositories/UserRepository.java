package com.example.kursova.repositories;

import com.example.kursova.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

// Репозиторій для користувачів
@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
}
