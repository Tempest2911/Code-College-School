package org.example.lamview10table.Repository;

import org.example.lamview10table.Model.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AppoinmentRepository extends JpaRepository<Appointment, Integer> {

}
