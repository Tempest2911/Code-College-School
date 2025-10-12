package org.example.lab6.Model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Nationalized;

import java.time.Instant;
import java.util.LinkedHashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "Appointments")
public class Appointment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "AppointmentID", nullable = false)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "PatientID")
    private Patient patientID;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "DoctorID")
    private Doctor doctorID;

    @Column(name = "AppointmentDate")
    private Instant appointmentDate;

    @Nationalized
    @Column(name = "Status", length = 50)
    private String status;

    @Nationalized
    @Column(name = "Reason")
    private String reason;

    @Column(name = "CreatedAt")
    private Instant createdAt;

    @OneToMany(mappedBy = "appointmentID")
    private Set<MedicalRecord> medicalRecords = new LinkedHashSet<>();

}