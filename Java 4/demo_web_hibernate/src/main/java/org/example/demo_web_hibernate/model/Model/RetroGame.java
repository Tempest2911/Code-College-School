package org.example.demo_web_hibernate.model.Model;

import jakarta.persistence.*;
import org.hibernate.annotations.Nationalized;

@Entity
public class RetroGame {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "GameID", nullable = false)
    private Integer id;

    @Nationalized
    @Column(name = "Title", nullable = false, length = 100)
    private String title;

    @Nationalized
    @Column(name = "Console", length = 50)
    private String console;

    @Column(name = "ReleaseYear")
    private Integer releaseYear;

    @Column(name = "IsMultiplayer")
    private int isMultiplayer;

    @Column(name = "Rating")
    private Double rating;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getConsole() {
        return console;
    }

    public void setConsole(String console) {
        this.console = console;
    }

    public Integer getReleaseYear() {
        return releaseYear;
    }

    public void setReleaseYear(Integer releaseYear) {
        this.releaseYear = releaseYear;
    }

    public int getIsMultiplayer() {
        return isMultiplayer;
    }

    public void setIsMultiplayer(int isMultiplayer) {
        this.isMultiplayer = isMultiplayer;
    }

    public Double getRating() {
        return rating;
    }

    public void setRating(Double rating) {
        this.rating = rating;
    }

}