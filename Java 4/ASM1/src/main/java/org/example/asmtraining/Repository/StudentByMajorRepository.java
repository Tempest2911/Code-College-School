package org.example.asmtraining.Repository;

import org.example.asmtraining.Model.Student;
import org.example.asmtraining.Util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.query.Query;
import java.util.List;

public class StudentByMajorRepository {
    public List<Student> getStudentsByMajor(String major) {
        try (Session session = HibernateUtil.getSession()) {
            Query<Student> query = session.createQuery("FROM Student WHERE major = :major", Student.class);
            query.setParameter("major", major);
            return query.getResultList();
        }
    }
}