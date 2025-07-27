package Repository;

import Model.Classes;
import Model.GymMember;
import Util.HibernateUtil;
import org.hibernate.query.Query;

import java.util.List;

public class MembersDetailRepo {
    public List<GymMember> getMembersDetail(Integer id) {
        try (var session = HibernateUtil.getSession()) {
            Query<GymMember> query = session.createQuery("FROM GymMember where id = :id", GymMember.class);
            query.setParameter("id", id);
            return query.getResultList();
        }
    }
}
