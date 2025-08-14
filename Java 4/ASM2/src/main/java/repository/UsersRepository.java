package repository;

import model.Users;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import util.HibernateUtil;

public class UsersRepository {

    public Users findByEmail(String email) {
        try (Session session = HibernateUtil.getSession()) {
            Query<Users> query = session.createQuery("FROM Users WHERE email = :email", Users.class);
            query.setParameter("email", email);
            return query.uniqueResult();
        }
    }

    public Users findByEmailAndPassword(String email, String password) {
        try (Session session = HibernateUtil.getSession()) {
            Query<Users> query = session.createQuery(
                    "FROM Users WHERE email = :email AND password = :password", Users.class);
            query.setParameter("email", email);
            query.setParameter("password", password);
            return query.uniqueResult();
        }
    }

    public boolean save(Users user) {
        Transaction tx = null;
        try (Session session = HibernateUtil.getSession()) {
            tx = session.beginTransaction();
            session.save(user);
            tx.commit();
            return true;
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
            return false;
        }
    }

    public boolean update(Users user) {
        Transaction tx = null;
        try (Session s = HibernateUtil.getSession()) {
            tx = s.beginTransaction();
            s.update(user);
            tx.commit();
            return true;
        } catch (Exception ex) {
            if (tx != null) tx.rollback();
            ex.printStackTrace();
            return false;
        }
    }
}
