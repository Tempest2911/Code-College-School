package org.example;
import org.hibernate.query.Query;
import org.hibernate.Session;
import java.util.List;
public class NhanVienRepository {


    public List<SimpleUser> getAllSimple() {
        Session session = HibernateUtil.getSession();
        Query<SimpleUser> query = session.createQuery("FROM SimpleUser su ", SimpleUser.class);
        List<SimpleUser> dsNhanVien = query.getResultList();
        return dsNhanVien;
    }

    public List<Contact> getAllContact() {
        Session session = HibernateUtil.getSession();
        Query<Contact> query = session.createQuery("FROM Contact ct ", Contact.class);
        List<Contact> dsNhanVien = query.getResultList();
        return dsNhanVien;
    }


    public List<Product> getAllProduct() {
        Session session = HibernateUtil.getSession();
        Query<Product> query = session.createQuery("FROM Product p ", Product.class);
        List<Product> dsNhanVien = query.getResultList();
        return dsNhanVien;
    }

    public List<Task> getAllTask() {
        Session session = HibernateUtil.getSession();
        Query<Task> query = session.createQuery("FROM Task t ", Task.class);
        List<Task> dsNhanVien = query.getResultList();
        return dsNhanVien;
    }

    public List<NewsletterSubscriber> getAllNewsletterSubscribers() {
        Session session = HibernateUtil.getSession();
        Query<NewsletterSubscriber> query = session.createQuery("FROM NewsletterSubscriber n ", NewsletterSubscriber.class);
        List<NewsletterSubscriber> dsNhanVien = query.getResultList();
        return dsNhanVien;
    }
}
