package Repository;

import Model.GymMember;
import Util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.query.Query;
import java.util.List;

public class GymMemberRepository {

    public List<GymMember> getGymMember() {
        try (Session session = HibernateUtil.getSession()) {
            Query<GymMember> query = session.createQuery("FROM GymMember", GymMember.class);
            return query.getResultList();
        }
    }


}
