package org.example.lamview10table.Repository;

import org.example.lamview10table.Model.Medicine;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MedicineRepository extends JpaRepository<Medicine, Integer> {
}

