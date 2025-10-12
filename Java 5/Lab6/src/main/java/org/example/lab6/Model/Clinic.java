package org.example.lab6.Model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Nationalized;

import java.time.LocalTime;
import java.util.LinkedHashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "Clinics")
public class Clinic {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ClinicID", nullable = false)
    private Integer id;

    @Nationalized
    @Column(name = "Name", length = 100)
    private String name;

    @Nationalized
    @Column(name = "Address", length = 200)
    private String address;

    @Nationalized
    @Column(name = "Phone", length = 20)
    private String phone;

    @Nationalized
    @Column(name = "Email", length = 100)
    private String email;

    @Column(name = "OpenTime")
    private LocalTime openTime;

    @Column(name = "CloseTime")
    private LocalTime closeTime;

    @OneToMany(mappedBy = "clinicID")
    private Set<Doctor> doctors = new LinkedHashSet<>();

}