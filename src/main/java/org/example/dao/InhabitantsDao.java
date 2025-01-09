package org.example.dao;

import org.hibernate.Session;
import org.example.entity.Blocks;
import org.example.entity.Inhabitants;
import org.example.configuration.SessionFactoryUtil;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import com.mysql.cj.conf.ConnectionUrlParser.Pair;
import com.sun.jdi.LongValue;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaDelete;


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

    public static List<Pair<String,Long>> sortYears(Boolean ageAscending) //Integer lessThan, Integer moreThan,String like)
    {
        Transaction transaction = null;
        try (Session session = SessionFactoryUtil.getSessionFactory().openSession()) {

            String hql = "SELECT mydb.Inhabitants.InhLastname as name,TIMESTAMPDIFF(YEAR, mydb.Inhabitants.InhDateOfBirth, NOW()) as age FROM mydb.Inhabitants ";

            if (ageAscending)  {
                hql += " ORDER BY age ASC";
            } else {
                hql += " ORDER BY age DESC";
            }
            Query query = session.createNativeQuery(hql);
            query.setMaxResults(10);
            List<Object[]> _res = query.list();

            List<Pair<String,Long>> res = new ArrayList<>();

            for (Object[] a: _res ) {
            BigInteger k = (BigInteger)a[1];
                res.add(new Pair<>( (String)a[0], k.longValue() ));
            }
            return res;
        }
    }

    public static List<Inhabitants> filterNames(String filter) //Integer lessThan, Integer moreThan,String like)
    {
        Transaction transaction = null;
        try (Session session = SessionFactoryUtil.getSessionFactory().openSession()) {

            String hql = "SELECT  i from Inhabitants i";
            Query query = null;
            if (filter != null) {
                hql += " WHERE i.inhFirstname like :filter";
                query = session.createQuery(hql);
                query.setParameter("filter", filter);
            } else {
                query = session.createQuery(hql);
            }

            query.setMaxResults(10);
            List<Inhabitants> _res = query.list();
            return _res;
        }
    }

    public static List<Inhabitants> getInhabitantsIn(Blocks b) { //Integer lessThan, Integer moreThan,String like)
        Transaction transaction = null;
        try (Session session = SessionFactoryUtil.getSessionFactory().openSession()) {

            String hql = "SELECT i FROM Inhabitants i INNER JOIN i.appartment a INNER JOIN a.block b WHERE b.blockId = :blid";

            Query query = session.createQuery(hql);
            query.setParameter("blid",b.getBlockId());
            List<Inhabitants> _res = query.list();

            return _res;
        }
    }


    public static List<Long>  getInhabitantsCountIn(Blocks b) { //Integer lessThan, Integer moreThan,String like)
        Transaction transaction = null;
        try (Session session = SessionFactoryUtil.getSessionFactory().openSession()) {

            String hql = "SELECT count(a) FROM Appartments a INNER JOIN a.block b WHERE b.blockId = :blid";

            Query query = session.createQuery(hql);
            query.setParameter("blid",b.getBlockId());
            List<Long> _res = query.list();

            return _res;
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

    public static void clear() {
        Transaction transaction = null;

        try (Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            CriteriaBuilder cb = session.getCriteriaBuilder();
            CriteriaDelete<Inhabitants> delete = cb.createCriteriaDelete(Inhabitants.class);
            delete.from(Inhabitants.class);
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
