package Repository;

import Model.Customer;
import Util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.boot.model.CustomSql;
import org.hibernate.query.Query;

import java.awt.print.Book;
import java.util.List;

public class Repo {

    public List<Customer> getAllBook() {
        try (Session session = HibernateUtil.getSession()) {
            Query<Customer  > query = session.createQuery("FROM Customer", Customer.class);
            return query.getResultList();
        }
    }
}
