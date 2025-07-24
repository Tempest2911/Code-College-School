package org.example;
import org.hibernate.query.Query;
import org.hibernate.Session;
import java.util.List;
public class SimpleUserRepository {
    public List<NhanVien> getAll() {
        Session session = HibernateUtil.getSession();
        Query<NhanVien> query = session.createQuery("FROM SimpleUser su ", NhanVien.class);
        List<NhanVien> dsNhanVien = query.getResultList();
        return dsNhanVien;
    }
}
