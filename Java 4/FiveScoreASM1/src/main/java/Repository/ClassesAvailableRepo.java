package Repository;

import Model.Classes;
import Util.HibernateUtil;
import org.hibernate.query.Query;

import java.util.List;

public class ClassesAvailableRepo {
    public List<Classes> getClassesAvailable() {
        try (var session = HibernateUtil.getSession()) {
            Query<Classes> query = session.createQuery("FROM Classes where max_participants = :available", Classes.class);
            query.setParameter("available", 0);
            return query.getResultList();
        }
    }
}
