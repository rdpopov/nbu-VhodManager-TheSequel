package org.example.dao;

import org.hibernate.Session;
import org.example.entity.Blocks;
import org.example.configuration.SessionFactoryUtil;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import java.util.List;


public class BlocksDao {

    // Save a new Block
    public void save(Blocks block) {
        Transaction transaction = null;
        try (Session session =  SessionFactoryUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.save(block);  // Save the Block object to the database
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();  // Rollback in case of error
            e.printStackTrace();
        }
    }

    // Find a Block by its ID
    public Blocks findById(int id) {
        try (Session session =  SessionFactoryUtil.getSessionFactory().openSession()) {
            return session.get(Blocks.class, id);  // Retrieve Block object by ID
        }
    }

    // Find all Blocks
    public List<Blocks> findAll() {
        try (Session session =  SessionFactoryUtil.getSessionFactory().openSession()) {
            Query<Blocks> query = session.createQuery("from Blocks", Blocks.class);
            return query.list();  // Return all Blocks in the database
        }
    }

    // Update an existing Block
    public void update(Blocks block) {
        Transaction transaction = null;
        try (Session session =  SessionFactoryUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.update(block);  // Update the Block object in the database
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();  // Rollback in case of error
            e.printStackTrace();
        }
    }

    // Delete a Block by its ID
    public void delete(int id) {
        Transaction transaction = null;
        try (Session session =  SessionFactoryUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            Blocks block = session.get(Blocks.class, id);
            if (block != null) {
                session.delete(block);  // Delete the Block from the database
            }
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();  // Rollback in case of error
            e.printStackTrace();
        }
    }
}
