package Repository;

import Model.Classes;
import Model.GymMember;
import Util.HibernateUtil;
import org.hibernate.query.Query;

import java.util.List;

public class ClassesDetailRepo {
    public List<Classes> getClassesDetail(Integer id) {
        try (var session = HibernateUtil.getSession()) {
            Query<Classes> query = session.createQuery("FROM Classes where id = :id", Classes.class);
            query.setParameter("id", id);
            return query.getResultList();
        }
    }

    public List<GymMember> getMembersClasses(Integer id) {
        try (var session = HibernateUtil.getSession()) {
            Query<GymMember> query = session.createQuery("select r.member from ClassRegistration r where r.classesField.id = :id", GymMember.class);
            query.setParameter("id", id);
            return query.getResultList();
        }
    }
}
