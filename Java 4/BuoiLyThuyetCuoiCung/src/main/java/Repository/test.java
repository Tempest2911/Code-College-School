package Repository;
// pls import HibernateConfig
import Model.StudentAttendance;
import Util.HibernateConfig;
import Util.HibernateUtil;
import org.hibernate.Session;

import org.hibernate.query.Query;
import java.util.List;

public class test {

    private Session session;

    public test() {
        session = HibernateConfig.getFACTORY().openSession();
    }

    public List<StudentAttendance> getAll() {
        return session.createQuery("FROM StudentAttendance").list();
    }

    public StudentAttendance getOne(Integer id) {
        return session.find(StudentAttendance.class, id);
    }

    public void update(StudentAttendance enityUpdate) {
        try {
            session.getTransaction().begin();
            session.merge(enityUpdate);
            session.getTransaction().commit();
        } catch (Exception e) {
            session.getTransaction().rollback();
            e.printStackTrace();
        }
    }

    public void delete(StudentAttendance enityDelete) {
        try {
            session.getTransaction().begin();
            session.delete(enityDelete);
            session.getTransaction().commit();
        } catch (Exception e) {
            session.getTransaction().rollback();
            e.printStackTrace();
        }
    }

    public void add(StudentAttendance enityAdd) {
        try {
            session.getTransaction().begin();
            session.save(enityAdd);
            session.getTransaction().commit();
        } catch (Exception e) {
            session.getTransaction().rollback();
            e.printStackTrace();
        }
    }
    
        public List<StudentAttendance> searchProducts(String keyword) {
        try (Session session = HibernateUtil.getSession()) {
            Query<StudentAttendance> query = session.createQuery("FROM StudentAttendance s WHERE s.studentName LIKE :kw", StudentAttendance.class);
            query.setParameter("kw", "%" + keyword + "%");
            return query.getResultList();
        }
    }
}