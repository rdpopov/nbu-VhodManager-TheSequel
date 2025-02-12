package org.example.dao;

import org.hibernate.Session;
import org.example.entity.Appartments;
import org.example.entity.Blocks;
import org.example.configuration.SessionFactoryUtil;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaDelete;

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

    public static void getTaxesForAppartment(Appartments appt) {
        Transaction transaction = null;
        try (Session session =  SessionFactoryUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();

            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();  // Rollback in case of error
            e.printStackTrace();
        }
    }


    public static List<Appartments> getAppartmentsInBlock(Blocks b) { //Integer lessThan, Integer moreThan,String like)
        Transaction transaction = null;
        try (Session session = SessionFactoryUtil.getSessionFactory().openSession()) {

            String hql = "SELECT a FROM Appartments a INNER JOIN a.block b WHERE b.blockId = :blid";

            Query query = session.createQuery(hql);
            query.setParameter("blid",b.getBlockId());
            List<Appartments> _res = query.list();

            return _res;
        }
    }


    public static List<Long> getAppartmentsInBlockCount(Blocks b) { //Integer lessThan, Integer moreThan,String like)
        Transaction transaction = null;
        try (Session session = SessionFactoryUtil.getSessionFactory().openSession()) {

            String hql = "SELECT count(a) FROM Appartments a INNER JOIN a.block b WHERE b.blockId = :blid";

            Query query = session.createQuery(hql);
            query.setParameter("blid",b.getBlockId());
            List<Long> _res = query.list();

            return _res;
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

    public static void clear() {
        Transaction transaction = null;

        try (Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            CriteriaBuilder cb = session.getCriteriaBuilder();
            CriteriaDelete<Appartments> delete = cb.createCriteriaDelete(Appartments.class);
            delete.from(Appartments.class);
            transaction = session.beginTransaction();
            session.createQuery(delete).executeUpdate();
            session.createNativeQuery("ALTER TABLE mydb.Appartments AUTO_INCREMENT = 1").executeUpdate();
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
        }
    }
}
