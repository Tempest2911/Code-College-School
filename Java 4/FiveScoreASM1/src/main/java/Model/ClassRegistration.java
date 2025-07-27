package Model;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "ClassRegistration")
public class ClassRegistration {
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private GymMember member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "class_id")
    private Classes classesField;

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

    public GymMember getMember() {
        return member;
    }

    public void setMember(GymMember member) {
        this.member = member;
    }

    public Classes getClassField() {
        return classesField;
    }

    public void setClassField(Classes classesField) {
        this.classesField = classesField;
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