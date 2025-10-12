package org.example.lamview10table.Repository;

import org.example.lamview10table.Model.Patient;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PatientRepository extends JpaRepository<Patient, Integer> {
}

