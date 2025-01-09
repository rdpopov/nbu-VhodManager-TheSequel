package org.example.configuration;

import org.example.entity.*;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

public class SessionFactoryUtil {
    private static SessionFactory sessionFactory;
    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            Configuration configuration = new Configuration();
            configuration.addAnnotatedClass(Company.class);
            configuration.addAnnotatedClass(Appartments.class);
            configuration.addAnnotatedClass(Inhabitants.class);
            configuration.addAnnotatedClass(Paid.class);
            configuration.addAnnotatedClass(Blocks.class);
            configuration.addAnnotatedClass(Employee.class);
            configuration.addAnnotatedClass(Owners.class);
            configuration.addAnnotatedClass(Tax.class);
            configuration.addAnnotatedClass(Pets.class);
            ServiceRegistry serviceRegistry
                    = new StandardServiceRegistryBuilder()
                    .applySettings(configuration.getProperties()).build();

            sessionFactory = configuration.buildSessionFactory(serviceRegistry);
        }
        return sessionFactory;
    }
}
