package org.example.onthifinaljava4.Repository;


import jakarta.persistence.Query;
import org.example.onthifinaljava4.Entity.SanPham;
import org.example.onthifinaljava4.Util.HibernateUtil;
import org.hibernate.Session;

import java.util.List;


public class SanPhamRepo {
    public List<Object[]> listSanPham() {
        Session session = HibernateUtil.getFactory().openSession();
        Query query = session.createQuery("select s.loaisp, s from SanPham s");
        List<Object[]> list = query.getResultList();
        session.close();
        return list;
    }

    public void AddSanPham(SanPham sanPham) {
        Session session = HibernateUtil.getFactory().openSession();
        try {
            session.beginTransaction();
            session.save(sanPham);
            session.getTransaction().commit();

        }catch (Exception e) {
            session.getTransaction().rollback();
            e.printStackTrace();
        }
    }

    public SanPham getSanPhamById(int id) {
        Session session = HibernateUtil.getFactory().openSession();
        return session.get(SanPham.class, id);
    }

    public List<Object[]> SpphanTrang(int pages, int pageSize) {
        Session session = HibernateUtil.getFactory().openSession();
        Query query = session.createQuery("select s.loaisp, s from SanPham s");
        query.setFirstResult(pages * pageSize);
        query.setMaxResults(pageSize);
        List<Object[]> list = query.getResultList();
        session.close();
        return list;
    }

    public void UpdateSanPham(SanPham sanPham) {
        Session session = HibernateUtil.getFactory().openSession();
        try {
            session.beginTransaction();
            session.merge(sanPham);
            session.getTransaction().commit();
        }catch (Exception e) {
            session.getTransaction().rollback();
            e.printStackTrace();
        }
    }

    public void DeleteSanPham(Integer id) {
        Session session = HibernateUtil.getFactory().openSession();
        try {
            session.beginTransaction();
            session.delete(this.getSanPhamById(id));
            session.getTransaction().commit();
        }catch (Exception e) {
            session.getTransaction().rollback();
            e.printStackTrace();
        }
    }

}
