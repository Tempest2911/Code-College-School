package org.example.demo_web_hibernate.model.Model;

import jakarta.persistence.*;
import org.hibernate.annotations.Nationalized;

@Entity
public class TalentPet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "PetID", nullable = false)
    private Integer id;

    @Nationalized
    @Column(name = "Name", length = 50)
    private String name;

    @Nationalized
    @Column(name = "Species", length = 50)
    private String species;

    @Nationalized
    @Column(name = "TrickPerformed", length = 100)
    private String trickPerformed;

    @Column(name = "Score")
    private Integer score;

    @Column(name = "IsFinalist")
    private Boolean isFinalist;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSpecies() {
        return species;
    }

    public void setSpecies(String species) {
        this.species = species;
    }

    public String getTrickPerformed() {
        return trickPerformed;
    }

    public void setTrickPerformed(String trickPerformed) {
        this.trickPerformed = trickPerformed;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public Boolean getIsFinalist() {
        return isFinalist;
    }

    public void setIsFinalist(Boolean isFinalist) {
        this.isFinalist = isFinalist;
    }

}