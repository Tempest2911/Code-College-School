package com.tc.bangkep.repo;
// pls import HibernateConfig

import com.tc.bangkep.model.NhanVien;
import com.tc.bangkep.util.HibernateConfig;
import org.hibernate.Session;

import java.util.List;

public class NhanVienRepo {

    private Session session;

    public NhanVienRepo() {
        session = HibernateConfig.getFACTORY().openSession();
    }

    public List<NhanVien> getAll() {
        return session.createQuery("FROM NhanVien").list();
    }

    public NhanVien getOne(Integer id) {
        return session.find(NhanVien.class, id);
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

    public void delete(NhanVien enityDelete) {
        try {
            session.getTransaction().begin();
            session.delete(enityDelete);
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
}
