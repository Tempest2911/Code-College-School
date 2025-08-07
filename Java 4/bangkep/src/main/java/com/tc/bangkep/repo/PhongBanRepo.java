package com.tc.bangkep.repo;
// pls import HibernateConfig

import com.tc.bangkep.model.PhongBan;
import com.tc.bangkep.util.HibernateConfig;
import org.hibernate.Session;

import java.util.List;

public class PhongBanRepo {

    private Session session;

    public PhongBanRepo() {
        session = HibernateConfig.getFACTORY().openSession();
    }

    public List<PhongBan> getAll() {
        return session.createQuery("FROM PhongBan").list();
    }

    public PhongBan getOne(Integer id) {
        return session.find(PhongBan.class, id);
    }
}
