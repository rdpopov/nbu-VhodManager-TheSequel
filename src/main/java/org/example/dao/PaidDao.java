package org.example.dao;

import org.hibernate.Session;
import org.example.entity.Paid;
import org.example.configuration.SessionFactoryUtil;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaDelete;

public class PaidDao {

    // Save a new Paid entry
    public static void save(Paid paid) {
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
    public static Paid findById(int paidId) {
        try (Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            return session.get(Paid.class, paidId);  // Retrieve Paid object by ID
        }
    }

    // Find all Paid entries
    public static List<Paid> findAll() {
        try (Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            Query<Paid> query = session.createQuery("from Paid", Paid.class);
            return query.list();  // Return all Paid entries in the database
        }
    }

    // Update an existing Paid entry
    public static void update(Paid paid) {
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
    public static void delete(int paidId) {
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

    public static void clear() {
        Transaction transaction = null;

        try (Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            CriteriaBuilder cb = session.getCriteriaBuilder();
            CriteriaDelete<Paid> delete = cb.createCriteriaDelete(Paid.class);
            delete.from(Paid.class);
            transaction = session.beginTransaction();
            session.createQuery(delete).executeUpdate();
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
        }
    }
}
