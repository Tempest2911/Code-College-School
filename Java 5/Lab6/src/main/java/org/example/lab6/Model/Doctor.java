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
@Table(name = "Doctors")
public class Doctor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "DoctorID", nullable = false)
    private Integer id;

    @Nationalized
    @Column(name = "FullName", length = 100)
    private String fullName;

    @Nationalized
    @Column(name = "Gender", length = 10)
    private String gender;

    @Nationalized
    @Column(name = "Phone", length = 20)
    private String phone;

    @Nationalized
    @Column(name = "Email", length = 100)
    private String email;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "SpecialtyID")
    private Specialty specialtyID;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ClinicID")
    private Clinic clinicID;

    @OneToMany(mappedBy = "doctorID")
    private Set<Appointment> appointments = new LinkedHashSet<>();

    @OneToMany(mappedBy = "doctorID")
    private Set<MedicalRecord> medicalRecords = new LinkedHashSet<>();

}