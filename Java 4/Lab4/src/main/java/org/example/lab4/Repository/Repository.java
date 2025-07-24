package org.example.lab4.Repository;
import org.example.lab4.Util.HibernateUtil;
import org.example.lab4.model.SimpleUser;
import org.example.lab4.model.Contact;
import org.example.lab4.model.Product;
import org.example.lab4.model.Task;
import org.example.lab4.model.NewsletterSubscriber;
import org.hibernate.query.Query;
import org.hibernate.Session;
import java.util.List;
public class Repository {


    public List<Contact> getAllContact() {
        try (Session session = HibernateUtil.getSession()) {
            Query<Contact> query = session.createQuery("FROM Contact", Contact.class);
            return query.getResultList();
        }
    }

    public List<SimpleUser> getAllSimpleUser() {
        try (Session session = HibernateUtil.getSession()) {
            Query<SimpleUser> query = session.createQuery("FROM SimpleUser", SimpleUser.class);
            return query.getResultList();
        }
    }

    public List<Product> getAllProduct() {
        try (Session session = HibernateUtil.getSession()) {
            Query<Product> query = session.createQuery("FROM Product", Product.class);
            return query.getResultList();
        }
    }

    public List<Task> getAllTask() {
        try (Session session = HibernateUtil.getSession()) {
            Query<Task> query = session.createQuery("FROM Task", Task.class);
            return query.getResultList();
        }
    }

    public List<NewsletterSubscriber> getAllNewsletterSubscriber() {
        try (Session session = HibernateUtil.getSession()) {
            Query<NewsletterSubscriber> query = session.createQuery("FROM NewsletterSubscriber", NewsletterSubscriber.class);
            return query.getResultList();
        }
    }

}
