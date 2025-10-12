package org.example.lab6.Model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Nationalized;

import java.util.LinkedHashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "Departments")
public class Department {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "DepartmentID", nullable = false)
    private Integer id;

    @Nationalized
    @Column(name = "Name", length = 100)
    private String name;

    @Nationalized
    @Column(name = "Description")
    private String description;

    @Nationalized
    @Column(name = "HeadOfDepartment", length = 100)
    private String headOfDepartment;

    @Nationalized
    @Column(name = "Phone", length = 20)
    private String phone;

    @Nationalized
    @Column(name = "Email", length = 100)
    private String email;

    @Nationalized
    @Column(name = "Location", length = 100)
    private String location;

    @OneToMany(mappedBy = "departmentID")
    private Set<Specialty> specialties = new LinkedHashSet<>();

}