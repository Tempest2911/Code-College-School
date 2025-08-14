package org.example.onthifinaljava4.Util;


import java.util.Properties;

import org.example.onthifinaljava4.Entity.LoaiSP;
import org.example.onthifinaljava4.Entity.SanPham;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import org.hibernate.service.ServiceRegistry;

public final class HibernateUtil {

    private static final SessionFactory FACTORY;

    static {
        Configuration conf = new Configuration();

        Properties properties = new Properties();
        //properties.put(Environment.DIALECT, "org.hibernate.dialect.SQLServerDialect");//Bản cũ của thày Phong, ko phân trang đc
        properties.put(Environment.DIALECT, "org.hibernate.dialect.SQLServer2012Dialect");
        properties.put(Environment.DRIVER, "com.microsoft.sqlserver.jdbc.SQLServerDriver");
        properties.put(Environment.URL, DbMetadata.getConnectString());
        properties.put(Environment.SHOW_SQL, "true"); // Hiển thị câu lệnh SQL thực hiện
        // properties.put(Environment.HBM2DDL_AUTO, "create"); // tự động sinh db

        conf.setProperties(properties);
        conf.addAnnotatedClass(LoaiSP.class);
        conf.addAnnotatedClass(SanPham.class);
//        conf.addAnnotatedClass(BorrowRequests.class);


        ServiceRegistry registry = new StandardServiceRegistryBuilder()
                .applySettings(conf.getProperties())
                .build();
        FACTORY = conf.buildSessionFactory(registry);
    }

    public static SessionFactory getFactory() {
        return FACTORY;
    }

    public static synchronized Session getSession() {
        return FACTORY.openSession();
    }

    public static void main(String[] args) {
        getFactory();
        System.out.println("Hibernate run successfully!");
    }
}







