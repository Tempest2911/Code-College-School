package Repository;

import Util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.List;

public class TopAttendanceRepo {
    public List<Object[]> getTop5Attendance() {
        try (var session = HibernateUtil.getSession()) {
            String hql = """    
            select m, count(r.id)
            from ClassRegistration r
            join r.member m
            where r.attendanceStatus = :status
            group by m.id, m.memberCode, m.fullName, m.email, m.phone, m.dob, m.joinDate, m.status
            order by count(r.id) desc
        """;
            return session.createQuery(hql, Object[].class)
                    .setParameter("status", "Attended")   // kiểm tra đúng chính tả dữ liệu DB
                    .setMaxResults(5)
                    .getResultList();
        }
    }

}
