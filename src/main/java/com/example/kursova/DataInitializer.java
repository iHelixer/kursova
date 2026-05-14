package com.example.kursova;

import com.example.kursova.User;
import com.example.kursova.repositories.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DataInitializer {

    @Bean
    public CommandLineRunner initDatabase(UserRepository userRepository) {
        return args -> {
            if (userRepository.findByEmail("admin@f1pulse.com") == null) {
                User admin = new User();
                admin.setFirstName("Super");
                admin.setLastName("Admin");
                admin.setEmail("admin@f1pulse.com");
                admin.setPassword("admin123");
                admin.setRole("ADMIN");


                userRepository.save(admin);
                System.out.println("Створено адміністратора: admin@f1pulse.com / admin123");
            }
        };
    }
}