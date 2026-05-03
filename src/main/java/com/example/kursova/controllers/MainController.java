package com.example.kursova.controllers;

import com.example.kursova.Driver;
import com.example.kursova.News;
import com.example.kursova.Race;
import com.example.kursova.Team;
import com.example.kursova.repositories.DriverRepository;
import com.example.kursova.repositories.NewsRepository;
import com.example.kursova.repositories.RaceRepository;
import com.example.kursova.repositories.TeamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class MainController {

    // Підключаємо наші репозиторії для доступу до бази даних
    @Autowired
    private NewsRepository newsRepository;
    @Autowired
    private DriverRepository driverRepository;
    @Autowired
    private TeamRepository teamRepository;
    @Autowired
    private RaceRepository raceRepository;

    /**
     * Обробляє GET-запит на головну сторінку ("/").
     * Метод генерує дані для віджетів "Latest News", "Top Driver" та "Top Team",
     * які є вихідними для відображення на стартовій сторінці сайту.
     */
    @GetMapping("/")
    public String homePage(Model model) {
        // Отримуємо 3 останні новини
        List<News> latestNews = newsRepository.findTop3ByOrderByPublishedDateDesc();
        // Отримуємо найкращого пілота
        Driver topDriver = driverRepository.findTopByOrderBySeasonPointsDesc();
        // Отримуємо найкращу команду
        Team topTeam = teamRepository.findTopByOrderByChampionshipPointsDesc();
        // Отримуємо всі гонки для календаря
        List<Race> calendar = raceRepository.findAll();

        // Передаємо ці дані у HTML-шаблон
        model.addAttribute("newsList", latestNews);
        model.addAttribute("topDriver", topDriver);
        model.addAttribute("topTeam", topTeam);
        model.addAttribute("calendar", calendar);

        // Повертає назву HTML-файлу (наприклад, index.html у папці templates)
        return "index";
    }

    /**
     * Обробляє запит на сторінку з усіма пілотами.
     * Завантажує повний список пілотів з БД для загального рейтингу.
     */
    @GetMapping("/drivers")
    public String driversPage(Model model) {
        List<Driver> allDrivers = driverRepository.findAll();
        model.addAttribute("drivers", allDrivers);
        return "drivers"; // Повертає шаблон drivers.html
    }

    /**
     * Обробляє запит на сторінку з усіма командами.
     * Формує список команд для відображення Кубка конструкторів.
     */
    @GetMapping("/teams")
    public String teamsPage(Model model) {
        List<Team> allTeams = teamRepository.findAll();
        model.addAttribute("teams", allTeams);
        return "teams"; // Повертає шаблон teams.html
    }

    /**
     * Обробляє запит на сторінку купівлі квитків.
     */
    @GetMapping("/tickets")
    public String ticketsPage(Model model) {
        List<Race> upcomingRaces = raceRepository.findAll();
        model.addAttribute("races", upcomingRaces);
        return "tickets"; // Повертає шаблон tickets.html
    }
}
