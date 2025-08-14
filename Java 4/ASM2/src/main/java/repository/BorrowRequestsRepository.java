package repository;

import model.BorrowRequests;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import util.HibernateUtil;

import java.util.List;

public class BorrowRequestsRepository {

    public boolean save(BorrowRequests request) {
        Transaction tx = null;
        try (Session session = HibernateUtil.getSession()) {
            tx = session.beginTransaction();
            session.save(request);
            tx.commit();
            return true;
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
            return false;
        }
    }

    public List<BorrowRequests> findByUserId(int userId) {
        try (Session session = HibernateUtil.getSession()) {
            String hql = "FROM BorrowRequests br " +
                    "JOIN FETCH br.book " +
                    "WHERE br.user.id = :userId";
            Query<BorrowRequests> query = session.createQuery(hql, BorrowRequests.class);
            query.setParameter("userId", userId);
            return query.list();
        }
    }

    public List<BorrowRequests> findAll() {
        try (Session session = HibernateUtil.getSession()) {
            String hql = "FROM BorrowRequests br " +
                    "JOIN FETCH br.user " +
                    "JOIN FETCH br.book";
            return session.createQuery(hql, BorrowRequests.class).list();
        }
    }

    public BorrowRequests findById(int id) {
        try (Session session = HibernateUtil.getSession()) {
            return session.get(BorrowRequests.class, id);
        }
    }

    public boolean update(BorrowRequests request) {
        Transaction tx = null;
        try (Session session = HibernateUtil.getSession()) {
            tx = session.beginTransaction();
            session.update(request);
            tx.commit();
            return true;
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
            return false;
        }
    }
}
