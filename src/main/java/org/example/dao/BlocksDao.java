package org.example.dao;

import org.hibernate.Session;
import org.example.entity.Blocks;
import org.example.entity.Company;
import org.example.entity.Employee;
import org.example.entity.Tax;
import org.example.configuration.SessionFactoryUtil;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import com.mysql.cj.conf.ConnectionUrlParser.Pair;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaDelete;


public class BlocksDao {
    // Save a new Block
    public static void save(Blocks block) {
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

    public static void saveForCompany(Blocks block, Integer compId) {
        Transaction transaction = null;
        try (Session session =  SessionFactoryUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();

            String hql = "SELECT em.empId FROM Blocks bl INNER JOIN bl.employee em WHERE em.company.companyId = :cmpid GROUP BY em.empId ORDER BY count(bl) ";
            Query query = session.createQuery(hql);
            query.setMaxResults(1);
            query.setParameter("cmpid", compId);
            List<Integer> res = query.list();
            if(res != null && res.size() == 1) {
                Employee e = session.get(Employee.class, res.get(0));  // Retrieve Block object by ID
                block.setEmployee(e);
                session.save(block);
            }
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();  // Rollback in case of error
            e.printStackTrace();
        }
    }

    public static List<Pair<Blocks,Double>> MoneyToBeCollected() //Integer lessThan, Integer moreThan,String like)
    {
        Transaction transaction = null;
        try (Session session = SessionFactoryUtil.getSessionFactory().openSession()) {

            String hql = "SELECT b, sum(p.payAmount) FROM Paid p INNER JOIN p.appartments a  INNER JOIN a.block b WHERE p.paidOn = null GROUP BY b.blockId";
            Query query = session.createQuery(hql);
            List<Object[]> _res = query.list();

            List<Pair<Blocks,Double>> res = new ArrayList<>();
            for (Object[] a: _res ) {
                res.add(new Pair<>((Blocks)a[0], (Double)a[1]));
            }

            return res;
        }
    }

    public static List<Pair<Blocks,Double>> MoneyCollected() //Integer lessThan, Integer moreThan,String like)
    {
        Transaction transaction = null;
        try (Session session = SessionFactoryUtil.getSessionFactory().openSession()) {

            String hql = "SELECT b, sum(p.payAmount) FROM Paid p INNER JOIN p.appartments a  INNER JOIN a.block b WHERE p.paidOn != null GROUP BY b.blockId";
            Query query = session.createQuery(hql);
            List<Object[]> _res = query.list();

            List<Pair<Blocks,Double>> res = new ArrayList<>();
            for (Object[] a: _res ) {
                res.add(new Pair<>((Blocks)a[0], (Double)a[1]));
            }

            return res;
        }
    }

    // Find a Block by its ID
    public static Blocks findById(int id) {
        try (Session session =  SessionFactoryUtil.getSessionFactory().openSession()) {
            return session.get(Blocks.class, id);  // Retrieve Block object by ID
        }
    }

    // Find all Blocks
    public static List<Blocks> findAll() {
        try (Session session =  SessionFactoryUtil.getSessionFactory().openSession()) {
            Query<Blocks> query = session.createQuery("from Blocks", Blocks.class);
            return query.list();  // Return all Blocks in the database
        }
    }

    // Update an existing Block
    public static void update(Blocks block) {
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
    public static void delete(int id) {
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

    public static void clear() {
        Transaction transaction = null;

        try (Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            CriteriaBuilder cb = session.getCriteriaBuilder();
            CriteriaDelete<Blocks> delete = cb.createCriteriaDelete(Blocks.class);
            delete.from(Blocks.class);
            transaction = session.beginTransaction();
            session.createQuery(delete).executeUpdate();
            session.createNativeQuery("ALTER TABLE mydb.Blocks AUTO_INCREMENT = 1").executeUpdate();
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
        }
    }
}
