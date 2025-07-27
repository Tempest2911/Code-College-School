package org.example.asmtraining.Repository;

import org.example.asmtraining.Model.Student;
import org.example.asmtraining.Util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.query.Query;
import java.util.List;

public class StudentHighGpaRepository {
    public List<Student> getHighGpaStudents() {
        try (Session session = HibernateUtil.getSession()) {
            Query<Student> query = session.createQuery("FROM Student WHERE gpa >= 3.0", Student.class);
            return query.getResultList();
        }
    }
}