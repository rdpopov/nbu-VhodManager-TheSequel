package org.example.dao;

import org.hibernate.Session;
import org.example.entity.Appartments;
import org.example.configuration.SessionFactoryUtil;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import java.util.List;

public class AppartmentsDao {

    // Save a new Appartment
    public static void save(Appartments appartment) {
        Transaction transaction = null;
        try (Session session =  SessionFactoryUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.save(appartment);  // Save the Appartment object to the database
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();  // Rollback in case of error
            e.printStackTrace();
        }
    }

    // Find an Appartment by its ID
    public static Appartments findById(int id) {
        try (Session session =  SessionFactoryUtil.getSessionFactory().openSession()) {
            return session.get(Appartments.class, id);  // Get Appartment object by ID
        }
    }

    // Find all Appartments
    public static List<Appartments> findAll() {
        try (Session session =  SessionFactoryUtil.getSessionFactory().openSession()) {
            Query<Appartments> query = session.createQuery("from Appartments", Appartments.class);
            return query.list();  // Return all Appartments in the database
        }
    }

    // Update an existing Appartment
    public static void update(Appartments appartment) {
        Transaction transaction = null;
        try (Session session =  SessionFactoryUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.update(appartment);  // Update the Appartment object in the database
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();  // Rollback in case of error
            e.printStackTrace();
        }
    }

    // Delete an Appartment by its ID
    public static void delete(int id) {
        Transaction transaction = null;
        try (Session session =  SessionFactoryUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            Appartments appartment = session.get(Appartments.class, id);
            if (appartment != null) {
                session.delete(appartment);  // Delete the Appartment from the database
            }
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();  // Rollback in case of error
            e.printStackTrace();
        }
    }
}
