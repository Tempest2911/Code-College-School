package org.example.demo_web_hibernate.model.Util;

import org.example.demo_web_hibernate.model.Model.Cupcake;
import org.example.demo_web_hibernate.model.Model.RetroGame;
import org.example.demo_web_hibernate.model.Model.TalentPet;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import org.hibernate.service.ServiceRegistry;

import java.util.Properties;

public final class HibernateUtil {

    private static final SessionFactory FACTORY;

    static {
        Configuration conf = new Configuration();

        Properties properties = new Properties();
        properties.put(Environment.DIALECT, "org.hibernate.dialect.SQLServerDialect");
        properties.put(Environment.DRIVER, "com.microsoft.sqlserver.jdbc.SQLServerDriver");
        properties.put(Environment.URL, DbMetadata.getConnectString());
        properties.put(Environment.SHOW_SQL, "true"); // Hiển thị câu lệnh SQL thực hiện
        // properties.put(Environment.HBM2DDL_AUTO, "create"); // tự động sinh db

        conf.setProperties(properties);
          conf.addAnnotatedClass(Cupcake.class);
        conf.addAnnotatedClass(RetroGame.class);
        conf.addAnnotatedClass(TalentPet.class);

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