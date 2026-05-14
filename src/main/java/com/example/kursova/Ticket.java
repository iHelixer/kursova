package com.example.kursova;

import jakarta.persistence.*;

@Entity
@Table(name = "tickets")
public class Ticket {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String raceName;
    private String round;
    private String dateRange;
    private String venue;
    private String category;
    private String price;
    private String flag;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getRaceName() { return raceName; }
    public void setRaceName(String raceName) { this.raceName = raceName; }
    public String getRound() { return round; }
    public void setRound(String round) { this.round = round; }
    public String getDateRange() { return dateRange; }
    public void setDateRange(String dateRange) { this.dateRange = dateRange; }
    public String getVenue() { return venue; }
    public void setVenue(String venue) { this.venue = venue; }
    public String getCategory() { return category; }
    public void setCategory(String category) { this.category = category; }
    public String getPrice() { return price; }
    public void setPrice(String price) { this.price = price; }
    public String getFlag() { return flag; }
    public void setFlag(String flag) { this.flag = flag; }
    public User getUser() { return user; }
    public void setUser(User user) { this.user = user; }
}