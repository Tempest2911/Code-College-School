package org.example.asm.DTO;

import lombok.Data;
import org.example.asm.Model.Task;

@Data
public class TaskDTO {
    private Integer id;
    private String title;
    private String description;
    private String status;
    private String priority;
    private String deadline;

    private SimpleUser assignedTo;
    private SimpleUser createdBy;
    private SimpleDepartment department;

    private String action; // CREATED / UPDATED / DELETED

    @Data
    public static class SimpleUser {
        private Integer id;
        private String fullName;

        public SimpleUser(Integer id, String fullName) {
            this.id = id;
            this.fullName = fullName;
        }
    }

    @Data
    public static class SimpleDepartment {
        private Integer id;
        private String name;

        public SimpleDepartment(Integer id, String name) {
            this.id = id;
            this.name = name;
        }
    }

    // ✅ Factory method convert Task -> TaskDTO
    public static TaskDTO fromEntity(Task task, String action) {
        TaskDTO dto = new TaskDTO();
        dto.setId(task.getId());
        dto.setTitle(task.getTitle());
        dto.setDescription(task.getDescription());
        dto.setStatus(task.getStatus());
        dto.setPriority(task.getPriority());
        dto.setAction(action);

        if (task.getDeadline() != null) {
            dto.setDeadline(task.getDeadline().toString());
        }

        if (task.getAssignedTo() != null) {
            dto.setAssignedTo(new SimpleUser(task.getAssignedTo().getId(), task.getAssignedTo().getFullName()));
        }

        if (task.getCreatedBy() != null) {
            dto.setCreatedBy(new SimpleUser(task.getCreatedBy().getId(), task.getCreatedBy().getFullName()));
        }

        if (task.getDepartment() != null) {
            dto.setDepartment(new SimpleDepartment(task.getDepartment().getId(), task.getDepartment().getName()));
        }

        return dto;
    }
}
