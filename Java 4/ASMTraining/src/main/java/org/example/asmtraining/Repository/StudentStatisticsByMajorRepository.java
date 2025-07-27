package org.example.asmtraining.Repository;

import org.example.asmtraining.Util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.query.Query;
import java.util.List;

public class StudentStatisticsByMajorRepository {
    public List<Object[]> getStatisticsByMajor() {
        try (Session session = HibernateUtil.getSession()) {
            Query<Object[]> query = session.createQuery(
                "SELECT major, COUNT(*) FROM Student GROUP BY major", Object[].class);
            return query.getResultList();
        }
    }
}