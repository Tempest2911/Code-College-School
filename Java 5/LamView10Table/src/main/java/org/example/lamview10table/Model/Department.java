package org.example.lamview10table.Model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Nationalized;

@Getter
@Setter
@Entity
@Table(name = "Departments")
public class Department {
    @Id
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

}