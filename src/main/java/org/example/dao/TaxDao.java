package org.example.dao;

import org.hibernate.Session;
import org.example.entity.Tax;
import org.example.configuration.SessionFactoryUtil;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaDelete;

public class TaxDao {

    // Save a new Tax entry
    public static void save(Tax tax) {
        Transaction transaction = null;
        try (Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.save(tax);  // Save the Tax object to the database
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();  // Rollback in case of error
            e.printStackTrace();
        }
    }

    // Find a Tax entry by ID
    public static Tax findById(int taxId) {
        try (Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            return session.get(Tax.class, taxId);  // Retrieve Tax object by ID
        }
    }

    // Find all Tax entries
    public static List<Tax> findAll() {
        try (Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            Query<Tax> query = session.createQuery("from Tax", Tax.class);
            return query.list();  // Return all Tax entries in the database
        }
    }

    // Update an existing Tax entry
    public static void update(Tax tax) {
        Transaction transaction = null;
        try (Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.update(tax);  // Update the Tax object in the database
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();  // Rollback in case of error
            e.printStackTrace();
        }
    }

    // Delete a Tax entry by ID
    public static void delete(int taxId) {
        Transaction transaction = null;
        try (Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            Tax tax = session.get(Tax.class, taxId);
            if (tax != null) {
                session.delete(tax);  // Delete the Tax object from the database
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
            CriteriaDelete<Tax> delete = cb.createCriteriaDelete(Tax.class);
            delete.from(Tax.class);
            transaction = session.beginTransaction();
            session.createQuery(delete).executeUpdate();
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
        }
    }
}
