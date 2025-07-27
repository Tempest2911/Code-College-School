package Repository;

import Model.ClassRegistration;
import Util.HibernateUtil;
import org.hibernate.Session;

public class ClassRegistrationRepo {
    public ClassRegistration addNew(ClassRegistration classRegistration) {
        try (Session session = HibernateUtil.getSession()) {
            session.beginTransaction();
            session.save(classRegistration);
            session.getTransaction().commit();
            return classRegistration;
        }
    }
}
