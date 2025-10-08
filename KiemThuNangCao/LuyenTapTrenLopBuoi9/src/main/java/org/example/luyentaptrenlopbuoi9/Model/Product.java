package org.example.luyentaptrenlopbuoi9.Model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Nationalized;

@Getter
@Setter
@Entity
@Table(name = "product")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    private Category category;

    @Column(name = "product_code", length = 100)
    private String productCode;

    @Nationalized
    @Column(name = "product_name", length = 100)
    private String productName;

    @Column(name = "price")
    private Double price;

    @Nationalized
    @Column(name = "description", length = 100)
    private String description;

}