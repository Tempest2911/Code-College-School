package org.example.lab6.Repository;

import org.example.lab6.Model.Department;
import org.example.lab6.Model.Specialty;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.Instant;
import java.util.List;

public interface SpecialtiesRepository extends JpaRepository<Specialty, Integer> {
    List<Specialty> findByDepartmentID(Department departmentID);
    List<Specialty> findByCreatedAtBetween(Instant start, Instant end);
}
