package org.example.lab6.Model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Nationalized;

@Getter
@Setter
@Entity
@Table(name = "Prescriptions")
public class Prescription {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "PrescriptionID", nullable = false)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "RecordID")
    private MedicalRecord recordID;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "MedicineID")
    private Medicine medicineID;

    @Nationalized
    @Column(name = "Dosage", length = 50)
    private String dosage;

    @Nationalized
    @Column(name = "Frequency", length = 50)
    private String frequency;

    @Nationalized
    @Column(name = "Duration", length = 50)
    private String duration;

    @Nationalized
    @Column(name = "Note")
    private String note;

}