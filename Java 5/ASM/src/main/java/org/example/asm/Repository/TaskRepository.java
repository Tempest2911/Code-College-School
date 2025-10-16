package org.example.asm.Repository;

import org.example.asm.Model.Task;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.time.LocalDateTime;
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


    List<Task> findByTitleContainingIgnoreCase(String keyword);

    @Query("SELECT t FROM Task t WHERE (:keyword IS NULL OR LOWER(t.title) LIKE LOWER(CONCAT('%', :keyword, '%'))) " +
            "AND (:departmentId IS NULL OR t.department.id = :departmentId) " +
            "AND (:startDate IS NULL OR t.deadline >= :startDate) " +
            "AND (:endDate IS NULL OR t.deadline <= :endDate)")
    List<Task> searchTasks(@Param("keyword") String keyword,
                           @Param("departmentId") Integer departmentId,
                           @Param("startDate") LocalDateTime startDate,
                           @Param("endDate") LocalDateTime endDate);

    @Query("SELECT t FROM Task t WHERE (:keyword IS NULL OR LOWER(t.title) LIKE LOWER(CONCAT('%', :keyword, '%'))) " +
            "AND (:departmentId IS NULL OR t.department.id = :departmentId) " +
            "AND (:startDate IS NULL OR t.deadline >= :startDate) " +
            "AND (:endDate IS NULL OR t.deadline <= :endDate)")
    Page<Task> searchTasksPage(@Param("keyword") String keyword,
                              @Param("departmentId") Integer departmentId,
                              @Param("startDate") LocalDateTime startDate,
                              @Param("endDate") LocalDateTime endDate,
                              Pageable pageable);

    @Query("select t from Task t order by t.id desc")
    List<Task> findAllByOrderByTaskIdDesc();

    @Query("select t from Task t order by t.id asc ")
    List<Task> findAllByOrderByTaskIdAsc();

    // Lấy các task có deadline trong khoảng thời gian chỉ định
    List<Task> findByDeadlineBetween(LocalDateTime start, LocalDateTime end);
}