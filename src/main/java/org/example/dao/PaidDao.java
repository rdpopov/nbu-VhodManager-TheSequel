package org.example.dao;

import org.hibernate.Session;
import org.example.entity.Paid;
import org.example.configuration.SessionFactoryUtil;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import java.util.List;

public class PaidDao {

    // Save a new Paid entry
    public void save(Paid paid) {
        Transaction transaction = null;
        try (Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.save(paid);  // Save the Paid object to the database
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();  // Rollback in case of error
            e.printStackTrace();
        }
    }

    // Find a Paid entry by ID
    public Paid findById(int paidId) {
        try (Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            return session.get(Paid.class, paidId);  // Retrieve Paid object by ID
        }
    }

    // Find all Paid entries
    public List<Paid> findAll() {
        try (Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            Query<Paid> query = session.createQuery("from Paid", Paid.class);
            return query.list();  // Return all Paid entries in the database
        }
    }

    // Update an existing Paid entry
    public void update(Paid paid) {
        Transaction transaction = null;
        try (Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.update(paid);  // Update the Paid object in the database
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();  // Rollback in case of error
            e.printStackTrace();
        }
    }

    // Delete a Paid entry by ID
    public void delete(int paidId) {
        Transaction transaction = null;
        try (Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            Paid paid = session.get(Paid.class, paidId);
            if (paid != null) {
                session.delete(paid);  // Delete the Paid object from the database
            }
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();  // Rollback in case of error
            e.printStackTrace();
        }
    }
}
