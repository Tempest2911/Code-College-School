package org.example.lamview10table.Model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Nationalized;

import java.time.LocalDate;

@Getter
@Setter
@Entity
@Table(name = "Patients")
public class Patient {
    @Id
    @Column(name = "PatientID", nullable = false)
    private Integer id;

    @Nationalized
    @Column(name = "FullName", length = 100)
    private String fullName;

    @Nationalized
    @Column(name = "Gender", length = 10)
    private String gender;

    @Column(name = "DateOfBirth")
    private LocalDate dateOfBirth;

    @Nationalized
    @Column(name = "Phone", length = 20)
    private String phone;

    @Nationalized
    @Column(name = "Email", length = 100)
    private String email;

    @Nationalized
    @Column(name = "Address", length = 200)
    private String address;

}