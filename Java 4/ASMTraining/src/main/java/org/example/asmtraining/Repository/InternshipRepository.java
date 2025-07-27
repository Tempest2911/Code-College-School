package org.example.asmtraining.Repository;

import org.example.asmtraining.Model.Internship;
import org.example.asmtraining.Util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.query.Query;
import java.util.List;

public class InternshipRepository {
    public List<Internship> getInternshipsByStudentId(Integer studentId) {
        try (Session session = HibernateUtil.getSession()) {
            Query<Internship> query = session.createQuery(
                "FROM Internship WHERE student.id = :studentId", Internship.class);
            query.setParameter("studentId", studentId);
            return query.getResultList();
        }
    }
}