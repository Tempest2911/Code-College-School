package Repository;


import Model.Book;
import Model.BorrowRecord;
import Model.Member;
import Util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.time.LocalDate;
import java.util.List;

public class Repository {
    public List<Book> getAllBook() {
        try (Session session = HibernateUtil.getSession()) {
            Query<Book> query = session.createQuery("FROM Book", Book.class);
            return query.getResultList();
        }
    }

    public List<Book> getBookByCategory(String category) {
        try (Session session = HibernateUtil.getSession()) {
            Query<Book> query = session.createQuery("FROM Book b WHERE b.category = :category", Book.class);
            query.setParameter("category", category);
            return query.getResultList();
        }
    }


    public List<Book> getBookavailable() {
        try (Session session = HibernateUtil.getSession()) {
            Query<Book> query = session.createQuery("FROM Book where quantity > 0", Book.class);
            return query.getResultList();
        }
    }

    public Book getBookDetail(Integer id) {
        try (Session session = HibernateUtil.getSession()) {
            return session.get(Book.class, id);
        }
    }

    public List<Member> getAllMember() {
        try (Session session = HibernateUtil.getSession()) {
            Query<Member> query = session.createQuery("FROM Member", Member.class);
            return query.getResultList();
        }
    }

    public List<Member> getMemberActive() {
        try (Session session = HibernateUtil.getSession()) {
            Query<Member> query = session.createQuery("FROM Member where status = 'Active'", Member.class);
            return query.getResultList();
        }
    }

    public Member getMemberDetail(Integer id) {
        try (Session session = HibernateUtil.getSession()) {
            return session.get(Member.class, id);
        }
    }

    public BorrowRecord addBorrowRecord(BorrowRecord borrowRecord) {
        try (Session session = HibernateUtil.getSession()) {
            session.beginTransaction();
            session.persist(borrowRecord);
            session.getTransaction().commit();
            return borrowRecord;
        }
    }

    public List<BorrowRecord> getOverdueRecords() {
        try (Session session = HibernateUtil.getSession()) {
            String hql = "FROM BorrowRecord WHERE status = 'Borrowed' AND dueDate < :today";
            Query<BorrowRecord> query = session.createQuery(hql, BorrowRecord.class);
            query.setParameter("today", LocalDate.now());
            return query.getResultList();
        }
    }

    public List<Object[]> getStatisticsByBook() {
        try (Session session = HibernateUtil.getSession()) {
            Query<Object[]> query = session.createQuery(
                    "SELECT category, COUNT(*) FROM Book GROUP BY category", Object[].class);
            return query.getResultList();
        }
    }

    public List<Object[]> getTopBorrowersBook() {
        try (Session session = HibernateUtil.getSession()) {
            Query<Object[]> query = session.createQuery(
                    "SELECT m, COUNT(br.id) AS borrowCount " +
                            "FROM BorrowRecord br " +
                            "JOIN br.member m " +
                            "GROUP BY m " +
                            "ORDER BY borrowCount DESC",
                    Object[].class
            );
            query.setMaxResults(5);
            return query.getResultList();
        }
    }

    public void returnBook(int borrowId) {
        try (Session session = HibernateUtil.getSession()) {
            session.beginTransaction();
            BorrowRecord record = session.get(BorrowRecord.class, borrowId);
            if (record != null) {
                record.setReturnDate(LocalDate.now());
                record.setStatus("Returned");
            }
            session.getTransaction().commit();
        }
    }

}