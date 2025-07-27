package org.example.asmtraining.Repository;

import org.example.asmtraining.Model.Student;
import org.example.asmtraining.Util.HibernateUtil;
import org.hibernate.Session;

public class StudentUpdateRepository {
    public Student updateStudent(Integer id, Student updatedStudent) {
        try (Session session = HibernateUtil.getSession()) {
            session.beginTransaction();
            Student student = session.get(Student.class, id);
            if (student != null) {
                student.setStudentCode(updatedStudent.getStudentCode());
                student.setFullName(updatedStudent.getFullName());
                student.setEmail(updatedStudent.getEmail());
                student.setPhone(updatedStudent.getPhone());
                student.setDob(updatedStudent.getDob());
                student.setGender(updatedStudent.getGender());
                student.setMajor(updatedStudent.getMajor());
                student.setGpa(updatedStudent.getGpa());
                student.setCreatedAt(updatedStudent.getCreatedAt());
                session.update(student);
            }
            session.getTransaction().commit();
            return student;
        }
    }
}