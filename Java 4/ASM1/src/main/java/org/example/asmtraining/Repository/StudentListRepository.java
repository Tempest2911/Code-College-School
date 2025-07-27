package org.example.asmtraining.Repository;

import org.example.asmtraining.Model.Student;
import org.example.asmtraining.Util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.query.Query;
import java.util.List;

public class StudentListRepository {
    public List<Student> getAllStudent() {
        try (Session session = HibernateUtil.getSession()) {
            Query<Student> query = session.createQuery("FROM Student", Student.class);
            return query.getResultList();
        }
    }
}