package Model;

import jakarta.persistence.*;
import org.hibernate.annotations.Nationalized;

import java.time.Instant;

@Entity
public class Class {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Nationalized
    @Column(name = "class_name", length = 100)
    private String className;

    @Nationalized
    @Column(name = "category", length = 50)
    private String category;

    @Column(name = "schedule_time")
    private Instant scheduleTime;

    @Column(name = "max_participants")
    private Integer maxParticipants;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Instant getScheduleTime() {
        return scheduleTime;
    }

    public void setScheduleTime(Instant scheduleTime) {
        this.scheduleTime = scheduleTime;
    }

    public Integer getMaxParticipants() {
        return maxParticipants;
    }

    public void setMaxParticipants(Integer maxParticipants) {
        this.maxParticipants = maxParticipants;
    }

}