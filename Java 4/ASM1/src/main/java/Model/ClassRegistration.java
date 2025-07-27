package Model;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "Class_Registration")
public class ClassRegistration {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "class_id")
    private Class classField;

    @Column(name = "register_date")
    private LocalDate registerDate;

    @Column(name = "attendance_status", length = 20)
    private String attendanceStatus;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Class getClassField() {
        return classField;
    }

    public void setClassField(Class classField) {
        this.classField = classField;
    }

    public LocalDate getRegisterDate() {
        return registerDate;
    }

    public void setRegisterDate(LocalDate registerDate) {
        this.registerDate = registerDate;
    }

    public String getAttendanceStatus() {
        return attendanceStatus;
    }

    public void setAttendanceStatus(String attendanceStatus) {
        this.attendanceStatus = attendanceStatus;
    }

}