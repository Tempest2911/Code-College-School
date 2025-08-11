package Repository;
// pls import HibernateConfig
import org.hibernate.Session;
import Model.Destination;
import Util.HibernateConfig;
import Util.HibernateUtil;
import org.hibernate.Session;

import org.hibernate.query.Query;
import java.util.List;


public class Repository {

    Session session = HibernateUtil.getSession();

    public List<Destination> getAll() {
        return session.createQuery("FROM Destination").list();
    }

    public Destination getOne(Integer id) {
        return session.find(Destination.class, id);
    }

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


    public void delete(Integer id) {
        try {
            session.getTransaction().begin();
            session.delete(getOne(id));
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
    
    public List<Destination> search(String keyword) {
        Query query = session.createQuery("FROM Destination sp WHERE sp.name LIKE :kw");
        query.setParameter("kw", "%" + keyword + "%");
        return query.list();
    }
    
    public List<Destination> soft() {
        Query query = session.createQuery("FROM Destination sp ORDER BY sp.name");
        return query.list();
    }
    
}