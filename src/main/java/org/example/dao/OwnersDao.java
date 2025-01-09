package org.example.dao;

import org.hibernate.Session;
import org.example.entity.Owners;
import org.example.configuration.SessionFactoryUtil;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaDelete;


public class OwnersDao {

    // Save a new Owner
    public static void save(Owners owner) {
        Transaction transaction = null;
        try (Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.save(owner);  // Save the Owner object to the database
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();  // Rollback in case of error
            e.printStackTrace();
        }
    }

    // Find an Owner by ID
    public static Owners findById(int ownerId) {
        try (Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            return session.get(Owners.class, ownerId);  // Retrieve Owner object by ID
        }
    }

    // Find all Owners
    public static List<Owners> findAll() {
        try (Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            Query<Owners> query = session.createQuery("from Owners", Owners.class);
            return query.list();  // Return all Owners in the database
        }
    }

    // Update an existing Owner
    public static void update(Owners owner) {
        Transaction transaction = null;
        try (Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.update(owner);  // Update the Owner object in the database
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();  // Rollback in case of error
            e.printStackTrace();
        }
    }

    // Delete an Owner by ID
    public static void delete(int ownerId) {
        Transaction transaction = null;
        try (Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            Owners owner = session.get(Owners.class, ownerId);
            if (owner != null) {
                session.delete(owner);  // Delete the Owner from the database
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
            CriteriaDelete<Owners> delete = cb.createCriteriaDelete(Owners.class);
            delete.from(Owners.class);
            transaction = session.beginTransaction();
            session.createQuery(delete).executeUpdate();
            session.createNativeQuery("ALTER TABLE mydb.Owners AUTO_INCREMENT = 1").executeUpdate();
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
        }
    }
}
