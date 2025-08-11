package Repository;
// pls import HibernateConfig
import org.hibernate.Session;
import Model.User;
import Util.HibernateConfig;
import Util.HibernateUtil;
import org.hibernate.Session;

import org.hibernate.query.Query;
import java.util.List;


public class UserRepo {

    Session session = HibernateUtil.getSession();

    public List<User> getAll() {
        return session.createQuery("FROM User").list();
    }

    public User userLogin(String email, String password) {
        try (Session session = HibernateUtil.getSession()) {
            String hql = "FROM User u WHERE u.email = :email AND u.password = :password";
            return session.createQuery(hql, User.class)
                    .setParameter("email", email)
                    .setParameter("password", password)
                    .uniqueResult();
        }
    }


    public User getOne(Integer id) {
        return session.find(User.class, id);
    }

    public void update(User enityUpdate) {
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

    public void add(User enityAdd) {
        try {
            session.getTransaction().begin();
            session.save(enityAdd);
            session.getTransaction().commit();
        } catch (Exception e) {
            session.getTransaction().rollback();
            e.printStackTrace();
        }
    }
//
//    public List<User> search(String keyword) {
//        Query query = session.createQuery("FROM User sp WHERE sp.[Đổi] LIKE :kw");
//        query.setParameter("kw", "%" + keyword + "%");
//        return query.list();
//    }
//
//    public List<User> soft() {
//        Query query = session.createQuery("FROM User sp ORDER BY sp.[Đổi]");
//        return query.list();
//    }
    
}