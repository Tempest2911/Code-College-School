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
@Table(name = "MedicalRecords")
public class MedicalRecord {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "RecordID", nullable = false)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "PatientID")
    private Patient patientID;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "DoctorID")
    private Doctor doctorID;

    @Nationalized
    @Column(name = "Diagnosis")
    private String diagnosis;

    @Column(name = "RecordDate")
    private Instant recordDate;

    @Nationalized
    @Column(name = "Note")
    private String note;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "AppointmentID")
    private Appointment appointmentID;

    @OneToMany(mappedBy = "recordID")
    private Set<Prescription> prescriptions = new LinkedHashSet<>();

}