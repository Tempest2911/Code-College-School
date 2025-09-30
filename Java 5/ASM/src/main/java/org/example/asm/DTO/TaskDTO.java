package org.example.asm.DTO;

import lombok.*;
import org.example.asm.Model.Task;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TaskDTO {
    private Integer id;
    private String title;
    private String status;
    private String priority;
    private String deadline;
    private String assignedTo;
    private String department;
    private String action; // CREATED / UPDATED / DELETED

    public TaskDTO(Task task, String action) {
        this.id = task.getId();
        this.title = task.getTitle();
        this.status = task.getStatus();
        this.assignedTo = (task.getAssignedTo() != null) ? task.getAssignedTo().getUsername() : null;
        this.department = (task.getDepartment() != null) ? task.getDepartment().getName() : null;
        this.action = action;
    }
}
