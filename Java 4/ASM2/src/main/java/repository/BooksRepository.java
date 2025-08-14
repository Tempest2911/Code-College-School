package repository;

import model.Books;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import util.HibernateUtil;

import java.util.Collections;
import java.util.List;

public class BooksRepository {

    public List<Books> findAll() {
        try (Session session = HibernateUtil.getSession()) {
            return session.createQuery("FROM Books", Books.class).list();
        }
    }

    public List<Books> searchByTitleOrAuthor(String keyword) {
        try (Session session = HibernateUtil.getSession()) {
            String hql = "FROM Books WHERE title LIKE :kw OR author LIKE :kw";
            Query<Books> query = session.createQuery(hql, Books.class);
            query.setParameter("kw", "%" + keyword + "%");
            return query.list();
        }
    }

    public Books findById(int id) {
        try (Session session = HibernateUtil.getSession()) {
            return session.get(Books.class, id);
        }
    }

    public boolean save(Books book) {
        Transaction tx = null;
        try (Session session = HibernateUtil.getSession()) {
            tx = session.beginTransaction();
            session.save(book);
            tx.commit();
            return true;
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
            return false;
        }
    }

    public boolean update(Books book) {
        Transaction tx = null;
        try (Session session = HibernateUtil.getSession()) {
            tx = session.beginTransaction();
            session.update(book);
            tx.commit();
            return true;
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
            return false;
        }
    }

    public boolean delete(int id) {
        Transaction tx = null;
        try (Session session = HibernateUtil.getSession()) {
            tx = session.beginTransaction();
            Books book = session.get(Books.class, id);
            if (book != null) {
                session.delete(book);
            }
            tx.commit();
            return true;
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
            return false;
        }
    }

    public List<Books> searchByTitleOrAuthorPaginated(String keyword, int page, int pageSize) {
        try (Session session = HibernateUtil.getSession()) {
            String hql = "FROM Books WHERE title LIKE :kw OR author LIKE :kw";
            return session.createQuery(hql, Books.class)
                    .setParameter("kw", "%" + keyword + "%")
                    .setFirstResult((page - 1) * pageSize)
                    .setMaxResults(pageSize)
                    .list();
        }
    }

    public long countAll() {
        try (Session session = HibernateUtil.getSession()) {
            return session.createQuery("SELECT COUNT(*) FROM Books", Long.class).uniqueResult();
        }
    }

    public long countSearch(String keyword) {
        try (Session session = HibernateUtil.getSession()) {
            String hql = "SELECT COUNT(*) FROM Books WHERE title LIKE :kw OR author LIKE :kw";
            return session.createQuery(hql, Long.class)
                    .setParameter("kw", "%" + keyword + "%")
                    .uniqueResult();
        }
    }


    public List<Books> findAllPaginated(int page, int size) {
        List<Books> allBooks = findAll();
        int fromIndex = (page - 1) * size;
        int toIndex = Math.min(fromIndex + size, allBooks.size());
        if (fromIndex > allBooks.size()) {
            return Collections.emptyList();
        }
        return allBooks.subList(fromIndex, toIndex);
    }


    public long countBooks() {
        try (Session session = HibernateUtil.getSession()) {
            String hql = "SELECT COUNT(b.id) FROM Books b";
            return session.createQuery(hql, Long.class).uniqueResult();
        }
    }

}
