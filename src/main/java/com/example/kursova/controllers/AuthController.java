package com.example.kursova.controllers;

import com.example.kursova.Ticket;
import com.example.kursova.User;
import com.example.kursova.repositories.TicketRepository;
import com.example.kursova.repositories.UserRepository;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class AuthController {
    private final UserRepository userRepository;
    private final TicketRepository ticketRepository;

    @Autowired
    public AuthController(UserRepository userRepository, TicketRepository ticketRepository) {
        this.userRepository = userRepository;
        this.ticketRepository = ticketRepository;
    }

    // Відображення сторінки входу/реєстрації
    @GetMapping("/signin")
    public String showSignInPage(jakarta.servlet.http.HttpSession session) {
        User currentUser = (User) session.getAttribute("loggedInUser");

        if (currentUser != null) {
            if ("ADMIN".equals(currentUser.getRole())) {
                return "redirect:/adminprofile";
            }
            return "redirect:/userprofile";
        }
        return "signin";
    }

    @GetMapping("/register")
    public String showRegisterPage() {
        return "register";
    }

    //ЛОГІКА РЕЄСТРАЦІЇ
    @PostMapping("/register")
    public String registerUser(@RequestParam String firstName,
                               @RequestParam String lastName,
                               @RequestParam String email,
                               @RequestParam String password) {
        if (userRepository.findByEmail(email) != null) {
            return "redirect:/register?error=email_exists";
        }

        User newUser = new User();
        newUser.setFirstName(firstName);
        newUser.setLastName(lastName);
        newUser.setEmail(email);
        newUser.setPassword(password);
        newUser.setRole("USER");

        userRepository.save(newUser);
        return "redirect:/signin?success";
    }

    //ЛОГІКА АВТОРИЗАЦІЇ
    @PostMapping("/login")
    public String loginUser(@RequestParam String email,
                            @RequestParam String password,
                            jakarta.servlet.http.HttpSession session) {

        User user = userRepository.findByEmail(email);

        if (user != null && user.getPassword().equals(password)) {
            session.setAttribute("loggedInUser", user);

            if ("ADMIN".equals(user.getRole())) {
                return "redirect:/adminprofile";
            }
            return "redirect:/userprofile";
        }

        return "redirect:/signin?error=wrong_credentials";
    }

    //ВИДАЛЕННЯ КВИТКА
    @PostMapping("/delete-ticket")
    public String deleteTicket(@RequestParam Long ticketId, jakarta.servlet.http.HttpSession session) {
        User currentUser = (User) session.getAttribute("loggedInUser");
        if (currentUser == null) {
            return "redirect:/signin";
        }

        ticketRepository.deleteById(ticketId);

        return "redirect:/userprofile";
    }

    // ПРОФІЛЬ АДМІНА
    @GetMapping("/adminprofile")
    public String showAdminProfile(jakarta.servlet.http.HttpSession session, org.springframework.ui.Model model) {
        User currentUser = (User) session.getAttribute("loggedInUser");

        if (currentUser == null || !"ADMIN".equals(currentUser.getRole())) {
            return "redirect:/signin";
        }

        model.addAttribute("adminUser", currentUser);
        model.addAttribute("allUsers", userRepository.findAll());

        long totalTicketsSold = ticketRepository.count();
        model.addAttribute("totalTicketsSold", totalTicketsSold);

        return "adminprofile";
    }

    //ВИХІД З СИСТЕМИ
    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/";
    }

    @PostMapping("/buy-ticket")
    public String buyTicket(@RequestParam String raceName, @RequestParam String round,
                            @RequestParam String dateRange, @RequestParam String venue,
                            @RequestParam String category, @RequestParam String price,
                            @RequestParam String flag, HttpSession session) {

        User currentUser = (User) session.getAttribute("loggedInUser");
        if (currentUser == null) {
            return "redirect:/signin";
        }

        Ticket newTicket = new Ticket();
        newTicket.setRaceName(raceName);
        newTicket.setRound(round);
        newTicket.setDateRange(dateRange);
        newTicket.setVenue(venue);
        newTicket.setCategory(category);
        newTicket.setPrice(price);
        newTicket.setFlag(flag);
        newTicket.setUser(currentUser);

        ticketRepository.save(newTicket);

        return "redirect:/userprofile";
    }

    @GetMapping("/userprofile")
    public String showUserProfile(jakarta.servlet.http.HttpSession session, org.springframework.ui.Model model) {
        User currentUser = (User) session.getAttribute("loggedInUser");
        if (currentUser == null) {
            return "redirect:/signin";
        }

        List<Ticket> myTickets = ticketRepository.findByUserOrderByIdDesc(currentUser);

        int totalTickets = myTickets.size();
        long uniqueEvents = myTickets.stream().map(Ticket::getRaceName).distinct().count();

        double totalSpent = 0.0;
        for (Ticket t : myTickets) {
            try {
                String cleanPrice = t.getPrice().replaceAll("[^0-9,]", "").replace(",", ".");
                if (!cleanPrice.isEmpty()) {
                    totalSpent += Double.parseDouble(cleanPrice);
                }
            } catch (Exception e) {
            }
        }

        String formattedSpent = String.format("€ %,.0f", totalSpent).replace(',', ' ');

        model.addAttribute("user", currentUser);
        model.addAttribute("tickets", myTickets);
        model.addAttribute("totalTickets", totalTickets);
        model.addAttribute("uniqueEvents", uniqueEvents);
        model.addAttribute("totalSpent", formattedSpent);

        return "userprofile";
    }

}