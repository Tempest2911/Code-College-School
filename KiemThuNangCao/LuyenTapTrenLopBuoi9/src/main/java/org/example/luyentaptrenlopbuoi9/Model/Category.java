package org.example.luyentaptrenlopbuoi9.Model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Nationalized;

import java.util.LinkedHashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "category")
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "category_code", length = 100)
    private String categoryCode;

    @Nationalized
    @Column(name = "category_name", length = 100)
    private String categoryName;

    @OneToMany(mappedBy = "category")
    private Set<Product> products = new LinkedHashSet<>();

}