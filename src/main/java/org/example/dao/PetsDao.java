package org.example.dao;

import org.hibernate.Session;
import org.example.entity.Pets;
import org.example.configuration.SessionFactoryUtil;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;


import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaDelete;
import java.util.List;

public class PetsDao {

    // Save a new Pet
    public static void save(Pets pet) {
        Transaction transaction = null;
        try (Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.save(pet); // Save the pet object
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    // Get a Pet by id
    public static Pets  findById(int idPets) {
        try (Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            return session.get(Pets.class, idPets); // Retrieve the pet object by ID
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    // Get all Pets
    public  static List<Pets>  findAll() {
        try (Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            Query<Pets> query = session.createQuery("FROM Pets", Pets.class);
            return query.getResultList(); // Retrieve all Pets as a list
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    // Update an existing Pet
    public static void update(Pets pet) {
        Transaction transaction = null;
        try (Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.update(pet); // Update the pet object
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    // Delete a Pet
    public static void delete(int idPets) {
        Transaction transaction = null;
        try (Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            Pets pet = session.get(Pets.class, idPets); // Retrieve the pet object by ID
            if (pet != null) {
                session.delete(pet); // Delete the pet
            }
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    public static void clear() {
        Transaction transaction = null;

        try (Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            CriteriaBuilder cb = session.getCriteriaBuilder();
            CriteriaDelete<Pets> delete = cb.createCriteriaDelete(Pets.class);
            delete.from(Pets.class);
            transaction = session.beginTransaction();
            session.createQuery(delete).executeUpdate();
            session.createNativeQuery("ALTER TABLE mydb.Inhabitants AUTO_INCREMENT = 1").executeUpdate();
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
        }
    }
}
