package org.example.asm.Repository;

import org.example.asm.Model.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface TaskRepository extends JpaRepository<Task, Integer> {
    List<Task> findByAssignedTo_Id(Integer userId); // lấy task theo user
    List<Task> findByAssignedTo_Department_Id(Integer departmentId);
    List<Task> findByDepartment_Id(Integer deptId);
    // Tìm task theo username của staff được assign
    List<Task> findByAssignedToUsername(String username);


}
