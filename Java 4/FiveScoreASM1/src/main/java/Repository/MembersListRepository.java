package Repository;

import Model.GymMember;
import Util.HibernateUtil;
import org.hibernate.query.Query;

import java.util.List;

public class MembersListRepository {

    public List<GymMember> getAllMembers() {
        try (var session = HibernateUtil.getSession()) {
            Query<GymMember> query = session.createQuery("FROM GymMember", GymMember.class);
            return query.getResultList();
        }
    }

}
