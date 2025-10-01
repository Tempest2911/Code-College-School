package org.example.asm.Repository;

import org.example.asm.Model.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TaskRepository extends JpaRepository<Task, Integer> {
    // Lấy tất cả task theo phòng ban
    List<Task> findByDepartmentId(Integer departmentId);

    // Lấy tất cả task gán cho một user cụ thể
    List<Task> findByAssignedTo_Id(Integer userId);

    // Nếu cần lọc thêm theo trạng thái
    List<Task> findByDepartmentIdAndStatus(Integer departmentId, String status);
    @Query("SELECT t.assignedTo.username, COUNT(t) " +
            "FROM Task t " +
            "WHERE t.status = 'Done' " +
            "GROUP BY t.assignedTo.username")
    List<Object[]> countTasksByUser();

    @Query("SELECT t FROM Task t WHERE (:status IS NULL OR t.status = :status)")
    List<Task> findByStatus(@Param("status") String status);




}
