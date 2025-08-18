package Repository;
// pls import HibernateConfig

import Model.LoaiPhongBan;
import org.hibernate.Session;
import Model.PhongBan;
import Util.HibernateConfig;
import Util.HibernateUtil;
import org.hibernate.Session;

import org.hibernate.query.Query;

import java.util.List;


public class Repository {

    Session session = HibernateUtil.getSession();

    public List<PhongBan> getAll(int page, int pageSize) {
        int offset = (page - 1) * pageSize;
        return session.createQuery("FROM PhongBan", PhongBan.class).setFirstResult(offset).setMaxResults(pageSize).getResultList();
    }

    public PhongBan getOne(Integer id) {
        return session.find(PhongBan.class, id);
    }

    public List<LoaiPhongBan> getLoaiPhongBan() {
        return session.createQuery("FROM LoaiPhongBan").list();
    }

    public LoaiPhongBan getLoaiPhongBanID(Integer id) {
        return session.get(LoaiPhongBan.class, id);
    }

    public void update(PhongBan enityUpdate) {
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

    public void add(PhongBan enityAdd) {
        try {
            session.getTransaction().begin();
            session.save(enityAdd);
            session.getTransaction().commit();
        } catch (Exception e) {
            session.getTransaction().rollback();
            e.printStackTrace();
        }
    }

    public List<PhongBan> search(String keyword) {
        Query query = session.createQuery("FROM PhongBan sp WHERE sp.ten LIKE :kw");
        query.setParameter("kw", "%" + keyword + "%");
        return query.list();
    }

    public List<PhongBan> soft() {
        Query query = session.createQuery("FROM PhongBan sp ORDER BY sp.ten");
        return query.list();
    }

    public long countAll() {
        return session.createQuery("SELECT COUNT(sp.id) FROM PhongBan sp", Long.class).getSingleResult();
    }

}