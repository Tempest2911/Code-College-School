package org.example.demotaco.model;

import jakarta.persistence.*;
import org.hibernate.annotations.Nationalized;

import java.time.LocalDate;

@Entity
public class Taco {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "TacoID", nullable = false)
    private Integer id;

    @Nationalized
    @Column(name = "Filling", nullable = false, length = 50)
    private String filling;

    @Nationalized
    @Column(name = "ShellType", length = 50)
    private String shellType;

    @Column(name = "SpiceLevel")
    private Integer spiceLevel;

    @Column(name = "IsVegetarian")
    private Boolean isVegetarian;

    @Column(name = "TastedOn")
    private LocalDate tastedOn;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFilling() {
        return filling;
    }

    public void setFilling(String filling) {
        this.filling = filling;
    }

    public String getShellType() {
        return shellType;
    }

    public void setShellType(String shellType) {
        this.shellType = shellType;
    }

    public Integer getSpiceLevel() {
        return spiceLevel;
    }

    public void setSpiceLevel(Integer spiceLevel) {
        this.spiceLevel = spiceLevel;
    }

    public Boolean getIsVegetarian() {
        return isVegetarian;
    }

    public void setIsVegetarian(Boolean isVegetarian) {
        this.isVegetarian = isVegetarian;
    }

    public LocalDate getTastedOn() {
        return tastedOn;
    }

    public void setTastedOn(LocalDate tastedOn) {
        this.tastedOn = tastedOn;
    }

}