package org.example.asmtraining.Repository;

import org.example.asmtraining.Model.Student;
import org.example.asmtraining.Util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.query.Query;
import java.util.List;

public class StudentNoInternshipRepository {
    public List<Student> getStudentsNoInternship() {
        try (Session session = HibernateUtil.getSession()) {
            Query<Student> query = session.createQuery(
                "SELECT s FROM Student s WHERE s.internships IS EMPTY", Student.class);
            return query.getResultList();
        }
    }
}