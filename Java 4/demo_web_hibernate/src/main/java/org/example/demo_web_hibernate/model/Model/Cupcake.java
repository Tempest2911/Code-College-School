package org.example.demo_web_hibernate.model.Model;

import jakarta.persistence.*;
import org.hibernate.annotations.Nationalized;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
public class Cupcake {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CupcakeID", nullable = false)
    private Integer id;

    @Nationalized
    @Column(name = "Flavor", nullable = false, length = 50)
    private String flavor;

    @Nationalized
    @Column(name = "Frosting", length = 50)
    private String frosting;

    @Column(name = "Price", nullable = false, precision = 5, scale = 2)
    private BigDecimal price;

    @Column(name = "IsGlutenFree")
    private int isGlutenFree;

    @Column(name = "BakedOn")
    private LocalDate bakedOn;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFlavor() {
        return flavor;
    }

    public void setFlavor(String flavor) {
        this.flavor = flavor;
    }

    public String getFrosting() {
        return frosting;
    }

    public void setFrosting(String frosting) {
        this.frosting = frosting;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public int getIsGlutenFree() {
        return isGlutenFree;
    }

    public void setIsGlutenFree(int isGlutenFree) {
        this.isGlutenFree = isGlutenFree;
    }

    public LocalDate getBakedOn() {
        return bakedOn;
    }

    public void setBakedOn(LocalDate bakedOn) {
        this.bakedOn = bakedOn;
    }

}