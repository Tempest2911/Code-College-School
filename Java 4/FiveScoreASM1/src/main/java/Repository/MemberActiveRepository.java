package Repository;

import Model.GymMember;
import Util.HibernateUtil;
import org.hibernate.query.Query;

import java.util.List;

public class MemberActiveRepository {
    public List<GymMember> getActiveMembers() {
        try (var session = HibernateUtil.getSession()) {
            Query<GymMember> query = session.createQuery("FROM GymMember where status = :status", GymMember.class);
            query.setParameter("status", "Active");
            return query.getResultList();
        }
    }
}
