package org.example.lamview10table.Model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Nationalized;

import java.math.BigDecimal;

@Getter
@Setter
@Entity
@Table(name = "Medicines")
public class Medicine {
    @Id
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

}