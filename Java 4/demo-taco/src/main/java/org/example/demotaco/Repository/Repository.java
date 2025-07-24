package org.example.demotaco.Repository;

import org.example.demotaco.Util.HibernateUtil;
import org.example.demotaco.model.Taco;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.List;

public class Repository {


    public List<Taco> getAllTaco() {
        try (Session session = HibernateUtil.getSession()) {
            Query<Taco> query = session.createQuery("FROM Taco", Taco.class);
            return query.getResultList();
        }
    }

//
//    public List<SimpleUser> getAllSimpleUser() {
//        try (Session session = HibernateUtil.getSession()) {
//            Query<SimpleUser> query = session.createQuery("FROM SimpleUser", SimpleUser.class);
//            return query.getResultList();
//        }
//    }
//
//    public List<Product> getAllProduct() {
//        try (Session session = HibernateUtil.getSession()) {
//            Query<Product> query = session.createQuery("FROM Product", Product.class);
//            return query.getResultList();
//        }
//    }
//
//    public List<Task> getAllTask() {
//        try (Session session = HibernateUtil.getSession()) {
//            Query<Task> query = session.createQuery("FROM Task", Task.class);
//            return query.getResultList();
//        }
//    }
//
//    public List<NewsletterSubscriber> getAllNewsletterSubscriber() {
//        try (Session session = HibernateUtil.getSession()) {
//            Query<NewsletterSubscriber> query = session.createQuery("FROM NewsletterSubscriber", NewsletterSubscriber.class);
//            return query.getResultList();
//        }
//    }

}
