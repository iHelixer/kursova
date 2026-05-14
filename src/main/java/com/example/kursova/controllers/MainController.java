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

    @Autowired
    private NewsRepository newsRepository;
    @Autowired
    private DriverRepository driverRepository;
    @Autowired
    private TeamRepository teamRepository;
    @Autowired
    private RaceRepository raceRepository;

    @GetMapping("/")
    public String homePage(Model model) {
        List<News> latestNews = newsRepository.findTop3ByOrderByPublishedDateDesc();
        Driver topDriver = driverRepository.findTopByOrderBySeasonPointsDesc();
        Team topTeam = teamRepository.findTopByOrderByChampionshipPointsDesc();
        List<Race> calendar = raceRepository.findAll();

        model.addAttribute("newsList", latestNews);
        model.addAttribute("topDriver", topDriver);
        model.addAttribute("topTeam", topTeam);
        model.addAttribute("calendar", calendar);

        return "index";
    }

    @GetMapping("/drivers")
    public String driversPage(Model model) {
        List<Driver> allDrivers = driverRepository.findAll();
        model.addAttribute("drivers", allDrivers);
        return "drivers";
    }

    @GetMapping("/teams")
    public String teamsPage(Model model) {
        List<Team> allTeams = teamRepository.findAll();
        model.addAttribute("teams", allTeams);
        return "teams";
    }

    @GetMapping("/tickets")
    public String ticketsPage(Model model) {
        List<Race> upcomingRaces = raceRepository.findAll();
        model.addAttribute("races", upcomingRaces);
        return "tickets";
    }

    @GetMapping("/calendar")
    public String calendarPage(Model model) {
        return "calendar";
    }


    @GetMapping("/driverprofile")
    public String driverProfile(Model model) {
        return "driverprofile";
    }

    @GetMapping("/teamprofile")
    public String teamProfile(Model model) {
        return "teamprofile";
    }



}
