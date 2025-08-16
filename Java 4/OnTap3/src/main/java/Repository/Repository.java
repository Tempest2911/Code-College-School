package Repository;
// pls import HibernateConfig

import Model.ChucVu;
import org.hibernate.Session;
import Model.NhanVien;
import Util.HibernateConfig;
import Util.HibernateUtil;
import org.hibernate.Session;

import org.hibernate.query.Query;

import java.util.List;


public class Repository {

    Session session = HibernateUtil.getSession();

    public List<NhanVien> getAll(int page, int pageSize) {
        int offset = (page - 1) * pageSize;
        return session.createQuery("FROM NhanVien", NhanVien.class).setFirstResult(offset).setMaxResults(pageSize).getResultList();
    }

    public NhanVien getOne(Integer id) {
        return session.find(NhanVien.class, id);
    }

    public List<ChucVu> getChucVu() {
        return session.createQuery("FROM ChucVu").list();
    }

    public ChucVu getChucVuID(Integer id) {
        return session.get(ChucVu.class, id);
    }

    public void update(NhanVien enityUpdate) {
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

    public void add(NhanVien enityAdd) {
        try {
            session.getTransaction().begin();
            session.save(enityAdd);
            session.getTransaction().commit();
        } catch (Exception e) {
            session.getTransaction().rollback();
            e.printStackTrace();
        }
    }

    public List<NhanVien> search(String keyword) {
        Query query = session.createQuery("FROM NhanVien sp WHERE sp.ten LIKE :kw");
        query.setParameter("kw", "%" + keyword + "%");
        return query.list();
    }

    public List<NhanVien> soft() {
        Query query = session.createQuery("FROM NhanVien sp ORDER BY sp.ten");
        return query.list();
    }



    public long countAll() {
        return session.createQuery("SELECT COUNT(sp.id) FROM NhanVien sp", Long.class).getSingleResult();
    }

}