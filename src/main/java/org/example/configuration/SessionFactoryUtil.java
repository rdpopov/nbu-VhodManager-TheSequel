package org.example.configuration;

import org.example.entity.Appartments;
import org.example.entity.Company;
import org.example.entity.Inhabitants;
import org.example.entity.Paid;
import org.example.entity.Blocks;
import org.example.entity.Employee;
import org.example.entity.Owners;
import org.example.entity.Tax;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

public class SessionFactoryUtil {
    private static SessionFactory sessionFactory;
    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            Configuration configuration = new Configuration();
            configuration.addAnnotatedClass(Appartments.class);
            configuration.addAnnotatedClass(Company.class);
            configuration.addAnnotatedClass(Inhabitants.class);
            configuration.addAnnotatedClass(Paid.class);
            configuration.addAnnotatedClass(Blocks.class);
            configuration.addAnnotatedClass(Employee.class);
            configuration.addAnnotatedClass(Owners.class);
            configuration.addAnnotatedClass(Tax.class);
            ServiceRegistry serviceRegistry
                    = new StandardServiceRegistryBuilder()
                    .applySettings(configuration.getProperties()).build();

            sessionFactory = configuration.buildSessionFactory(serviceRegistry);
        }
        return sessionFactory;
    }
}
