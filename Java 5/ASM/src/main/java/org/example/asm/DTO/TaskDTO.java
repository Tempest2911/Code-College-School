package org.example.asm.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.asm.Model.Task;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TaskDTO {
    private Integer id;
    private String title;
    private String description;
    private String status;
    private String priority;
    private String deadline;

    private String assignedToName;
    private String departmentName;
    private String createdByName;

    private String action;

    private Integer assignedToId;

    public static TaskDTO fromEntity(Task task, String action) {
        return new TaskDTO(
                task.getId(),
                task.getTitle(),
                task.getDescription(),
                task.getStatus(),
                task.getPriority(),
                task.getDeadline() != null ? task.getDeadline().toString() : null,
                task.getAssignedTo() != null ? task.getAssignedTo().getFullName() : null,
                task.getDepartment() != null ? task.getDepartment().getName() : null,
                task.getCreatedBy() != null ? task.getCreatedBy().getFullName() : null,
                action,
                task.getAssignedTo() != null ? task.getAssignedTo().getId() : null
        );
    }
}
