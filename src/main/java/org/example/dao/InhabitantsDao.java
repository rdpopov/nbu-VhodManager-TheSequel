package org.example.dao;

import org.hibernate.Session;
import org.example.entity.Inhabitants;
import org.example.configuration.SessionFactoryUtil;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import java.util.List;


public class InhabitantsDao {

    // Save a new Inhabitant
    public static void save(Inhabitants inhabitants) {
        Transaction transaction = null;
        try (Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.save(inhabitants);  // Save the Inhabitant object to the database
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();  // Rollback in case of error
            e.printStackTrace();
        }
    }

    // Find an Inhabitant by ID
    public static Inhabitants findById(int inhId) {
        try (Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            return session.get(Inhabitants.class, inhId);  // Retrieve Inhabitant object by ID
        }
    }

    // Find all Inhabitants
    public static List<Inhabitants> findAll() {
        try (Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            Query<Inhabitants> query = session.createQuery("from Inhabitants", Inhabitants.class);
            return query.list();  // Return all Inhabitants in the database
        }
    }

    // Update an existing Inhabitant
    public static void update(Inhabitants inhabitants) {
        Transaction transaction = null;
        try (Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.update(inhabitants);  // Update the Inhabitant object in the database
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();  // Rollback in case of error
            e.printStackTrace();
        }
    }

    // Delete an Inhabitant by ID
    public static void delete(int inhId) {
        Transaction transaction = null;
        try (Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            Inhabitants inhabitants = session.get(Inhabitants.class, inhId);
            if (inhabitants != null) {
                session.delete(inhabitants);  // Delete the Inhabitant from the database
            }
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();  // Rollback in case of error
            e.printStackTrace();
        }
    }
}
