package Repository;

import Model.Classes;
import Model.GymMember;
import Util.HibernateUtil;
import org.hibernate.query.Query;

import java.util.List;

public class ClassesListRepo {
    public List<Classes> getAllClass() {
        try (var session = HibernateUtil.getSession()) {
            Query<Classes> query = session.createQuery("FROM Classes", Classes.class);
            return query.getResultList();
        }
    }
}
