package org.example.asmtraining.Repository;

import org.example.asmtraining.Model.Student;
import org.example.asmtraining.Util.HibernateUtil;
import org.hibernate.Session;

public class StudentAddRepository {
    public Student addStudent(Student student) {
        try (Session session = HibernateUtil.getSession()) {
            session.beginTransaction();
            session.save(student);
            session.getTransaction().commit();
            return student;
        }
    }
}