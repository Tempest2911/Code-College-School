package org.example.asmtraining.Repository;

import org.example.asmtraining.Model.Student;
import org.example.asmtraining.Util.HibernateUtil;
import org.hibernate.Session;

public class StudentDetailRepository {
    public Student getStudentDetail(Integer id) {
        try (Session session = HibernateUtil.getSession()) {
            return session.get(Student.class, id);
        }
    }
}