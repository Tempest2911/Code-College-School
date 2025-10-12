package org.example.lab6.Model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Nationalized;

import java.math.BigDecimal;
import java.util.LinkedHashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "Medicines")
public class Medicine {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "MedicineID", nullable = false)
    private Integer id;

    @Nationalized
    @Column(name = "Name", length = 100)
    private String name;

    @Nationalized
    @Column(name = "Description")
    private String description;

    @Nationalized
    @Column(name = "Manufacturer", length = 100)
    private String manufacturer;

    @Nationalized
    @Column(name = "Unit", length = 50)
    private String unit;

    @Column(name = "Price", precision = 10, scale = 2)
    private BigDecimal price;

    @Column(name = "Stock")
    private Integer stock;

    @OneToMany(mappedBy = "medicineID")
    private Set<Prescription> prescriptions = new LinkedHashSet<>();

}