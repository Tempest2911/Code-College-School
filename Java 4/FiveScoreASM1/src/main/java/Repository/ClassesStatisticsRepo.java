package Repository;

import Util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.List;

public class ClassesStatisticsRepo {
    public List<Object[]> getClassesStatistics() {
        try (Session session = HibernateUtil.getSession()) {
            Query<Object[]> query = session.createQuery(
                    "SELECT c.category, COUNT(c.id) FROM Classes c GROUP BY c.category", Object[].class);
            return query.getResultList();
        }
    }
}
