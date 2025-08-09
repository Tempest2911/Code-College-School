package Repository;
// pls import HibernateConfig

import Model.Destination;
import Util.HibernateUtil;
import org.hibernate.Session;

import Util.HibernateConfig;

import java.util.List;


public class Repository {

    private Session session;

    public Repository() {
        session = HibernateConfig.getFACTORY().openSession();
    }

    public List<Destination> getAll() {
        try (Session session = HibernateUtil.getSession()) {
            return session.createQuery("FROM Destination").list();
        }
    }
//
//    public Destination getOne(Integer id) {
//        return session.find(Destination.class, id);
//    }

    public void update(Destination enityUpdate) {
        try {
            session.getTransaction().begin();
            session.merge(enityUpdate);
            session.getTransaction().commit();
        } catch (Exception e) {
            session.getTransaction().rollback();
            e.printStackTrace();
        }
    }

    public void delete(Integer enityDelete) {
        try {
            session.getTransaction().begin();
            session.delete(enityDelete);
            session.getTransaction().commit();
        } catch (Exception e) {
            session.getTransaction().rollback();
            e.printStackTrace();
        }
    }

    public void add(Destination enityAdd) {
        try {
            session.getTransaction().begin();   
            session.save(enityAdd);
            session.getTransaction().commit();
        } catch (Exception e) {
           session.getTransaction().rollback();
           e.printStackTrace();
        }
    }

    public Destination getDestinationById(Integer id) {
        try (Session s = HibernateUtil.getSession()) {
            return s.get(Destination.class, id);
        }
    }

//    public List<Destination> enitySearch(String keyword) {
//        try (Session session = HibernateUtil.getSession()) {
//            Query<Destination> query = session.createQuery("FROM Destination s WHERE s.studentName LIKE :kw", Destination.class);
//            query.setParameter("kw", "%" + keyword + "%");
//            return query.getResultList();
//        }
//    }
}