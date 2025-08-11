package Repository;
// pls import HibernateConfig

import org.hibernate.Session;
import Model.Book;
import Util.HibernateConfig;
import Util.HibernateUtil;
import org.hibernate.Session;

import org.hibernate.query.Query;

import java.util.List;


public class BookRepo {

    Session session = HibernateUtil.getSession();

    public List<Book> getAll() {
        return session.createQuery("FROM Book").list();
    }

    public Book getOne(Integer id) {
        return session.find(Book.class, id);
    }

    public void update(Book enityUpdate) {
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

    public void add(Book enityAdd) {
        try {
            session.getTransaction().begin();
            session.save(enityAdd);
            session.getTransaction().commit();
        } catch (Exception e) {
            session.getTransaction().rollback();
            e.printStackTrace();
        }
    }

    public List<Book> searchByTitle(String keyword) {
        Query query = session.createQuery("FROM Book sp WHERE sp.title LIKE :kw");
        query.setParameter("kw", "%" + keyword + "%");
        return query.list();
    }

    public List<Book> searchByAuthor(String keyword) {
        Query query = session.createQuery("FROM Book sp WHERE sp.author LIKE :kw");
        query.setParameter("kw", "%" + keyword + "%");
        return query.list();
    }

//    public List<Book> soft() {
//        Query query = session.createQuery("FROM Book sp ORDER BY sp.[Đổi]");
//        return query.list();
//    }

}