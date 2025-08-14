package org.example.onthifinaljava4.Repository;

import jakarta.persistence.Query;
import org.example.onthifinaljava4.Entity.LoaiSP;
import org.example.onthifinaljava4.Util.HibernateUtil;
import org.hibernate.Session;

import java.util.List;

public class LoaiSanPhamRepo {

    public List<LoaiSP> getLoaiSanPham() {
        Session session = HibernateUtil.getFactory().openSession();
        Query query = session.createQuery("from LoaiSP");
        List<LoaiSP> loaiSanPham = query.getResultList();
        session.close();
        return loaiSanPham;
    }

    public LoaiSP getLoaiSanPhamById(int id) {
        Session session = HibernateUtil.getFactory().openSession();
        return session.get(LoaiSP.class, id);
    }

}
