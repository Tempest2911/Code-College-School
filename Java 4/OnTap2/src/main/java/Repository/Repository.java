package Repository;
// pls import HibernateConfig
import Model.LoaiPhieuGiamGia;
import org.hibernate.Session;
import Model.PhieuGiamGia;
import Util.HibernateConfig;
import Util.HibernateUtil;
import org.hibernate.Session;

import org.hibernate.query.Query;
import java.util.List;


public class Repository {

    Session session = HibernateUtil.getSession();

    public List<PhieuGiamGia> getAll(int page, int pageSize) {
        int offset = (page - 1) * pageSize;
        return session.createQuery("FROM PhieuGiamGia",PhieuGiamGia.class).setFirstResult(offset).setMaxResults(pageSize).getResultList();
    }

//    public List<PhieuGiamGia> getAll() {
//        return session.createQuery("FROM PhieuGiamGia").list();
//    }

    public PhieuGiamGia getOne(Integer id) {
        return session.find(PhieuGiamGia.class, id);
    }

    public List<LoaiPhieuGiamGia> getLoaiPhieuGiamGia() {
        return session.createQuery("FROM LoaiPhieuGiamGia").list();
    }

    public LoaiPhieuGiamGia getLoaiPhieuGiamGiaID(Integer id) {
        return session.get(LoaiPhieuGiamGia.class, id);
    }

    public void update(PhieuGiamGia enityUpdate) {
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

    public void add(PhieuGiamGia enityAdd) {
        try {
            session.getTransaction().begin();
            session.save(enityAdd);
            session.getTransaction().commit();
        } catch (Exception e) {
            session.getTransaction().rollback();
            e.printStackTrace();
        }
    }
    
    public List<PhieuGiamGia> search(String keyword) {
        Query query = session.createQuery("FROM PhieuGiamGia sp WHERE sp.ten LIKE :kw");
        query.setParameter("kw", "%" + keyword + "%");
        return query.list();
    }
    
    public List<PhieuGiamGia> soft() {
        Query query = session.createQuery("FROM PhieuGiamGia sp ORDER BY sp.ten");
        return query.list();
    }
    
    public long countAll() {
        return session.createQuery("SELECT COUNT(sp.id) FROM PhieuGiamGia sp", Long.class).getSingleResult();
    }

}