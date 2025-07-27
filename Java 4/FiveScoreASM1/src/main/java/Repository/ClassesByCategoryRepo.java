package Repository;

import Model.Classes;
import Model.GymMember;
import Util.HibernateUtil;
import org.hibernate.query.Query;

import java.util.List;

public class ClassesByCategoryRepo {
    public List<Classes> getClassesCategory(String category) {
        try (var session = HibernateUtil.getSession()) {
            Query<Classes> query = session.createQuery("FROM Classes where category = :category", Classes.class);
            query.setParameter("category", category);
            return query.getResultList();
        }
    }
}
