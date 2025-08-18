package Repository;
// pls import HibernateConfig
import Model.LoaiSp;
import org.hibernate.Session;
import Model.SanPham;
import Util.HibernateConfig;
import Util.HibernateUtil;
import org.hibernate.Session;

import org.hibernate.query.Query;
import java.util.List;


public class Repository {

    Session session = HibernateUtil.getSession();

    public List<SanPham> getAll(int page, int pageSize) {
        int offset = (page - 1) * pageSize;
        return session.createQuery("FROM SanPham",SanPham.class).setFirstResult(offset).setMaxResults(pageSize).getResultList();
    }

    public SanPham getOne(Integer id) {
        return session.find(SanPham.class, id);
    }

    public List<LoaiSp> getLoaiSp() {
        return session.createQuery("FROM LoaiSp").list();
    }

    public LoaiSp getLoaiSpID(Integer id) {
        return session.get(LoaiSp.class, id);
    }

    public void update(SanPham enityUpdate) {
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

    public void add(SanPham enityAdd) {
        try {
            session.getTransaction().begin();
            session.save(enityAdd);
            session.getTransaction().commit();
        } catch (Exception e) {
            session.getTransaction().rollback();
            e.printStackTrace();
        }
    }
    
    public List<SanPham> search(String keyword) {
        Query query = session.createQuery("FROM SanPham sp WHERE sp.ten LIKE :kw");
        query.setParameter("kw", "%" + keyword + "%");
        return query.list();
    }
    
    public List<SanPham> soft() {
        Query query = session.createQuery("FROM SanPham sp ORDER BY sp.ten");
        return query.list();
    }
    
    public long countAll() {
        return session.createQuery("SELECT COUNT(sp.id) FROM SanPham sp", Long.class).getSingleResult();
    }

}