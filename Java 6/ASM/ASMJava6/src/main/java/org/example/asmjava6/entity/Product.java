package org.example.asmjava6.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.Nationalized;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.time.LocalDate;
import java.util.LinkedHashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "Products")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id", nullable = false)
    private Integer id;

    @Nationalized
    @Column(name = "Name", nullable = false, length = 50)
    private String name;

    @Nationalized
    @Column(name = "Image", nullable = false, length = 50)
    private String image;

    @Column(name = "Price", nullable = false)
    private Double price;

    @ColumnDefault("getdate()")
    @Column(name = "CreateDate", nullable = false)
    private LocalDate createDate;

    @ColumnDefault("1")
    @Column(name = "Available", nullable = false)
    private Boolean available = false;

    // @JsonIgnore  <-- XÓA DÒNG NÀY ĐI
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "CategoryId", nullable = false)
    private Category category;

    @JsonIgnore
    @OneToMany(mappedBy = "product")
    private Set<OrderDetail> orderDetails = new LinkedHashSet<>();

}