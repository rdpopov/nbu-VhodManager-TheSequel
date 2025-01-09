package org.example.dao;

import org.hibernate.Session;
import org.example.entity.Company;
import org.example.entity.Paid;
import org.example.configuration.SessionFactoryUtil;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import com.mysql.cj.conf.ConnectionUrlParser.Pair;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.persistence.criteria.CriteriaDelete;

public class CompanyDao {

    // Save a new Company
    public static void save(Company company) {
        Transaction transaction = null;
        try (Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.save(company);  // Save the Company object to the database
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();  // Rollback in case of error
            e.printStackTrace();
        }
    }

    // Find a Company by its ID
    public static Company findById(int id) {
        try (Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            return session.get(Company.class, id);  // Retrieve Company object by ID
        }
    }

    // Find all Companies
    public static List<Company> findAll() {
        try (Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            Query<Company> query = session.createQuery("from Company", Company.class);
            return query.list();  // Return all Companies in the database
        }
    }

    // Update an existing Company
    public static void update(Company company) {
        Transaction transaction = null;
        try (Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.update(company);  // Update the Company object in the database
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();  // Rollback in case of error
            e.printStackTrace();
        }
    }


    public static List<Pair<Company,Double>> filterCompaniesOnAllIncome(Boolean ascending) //Integer lessThan, Integer moreThan,String like)
    {
        Transaction transaction = null;
        try (Session session = SessionFactoryUtil.getSessionFactory().openSession()) {

            // String hql = "SELECT c.companyId FROM Paid p INNER JOIN Appartments a INNER JOIN Blocks b INNER JOIN Employee e INNER JOIN Company c GROUP BY c.companyId" ;
            // String hql = "SELECT c.companyId FROM Paid p INNER JOIN Company c WHERE p.appartments.block.employee.company.companyId = c.companyId GROUP BY c.companyId";
            String hql = "SELECT c, sum(p.payAmount) FROM Paid p INNER JOIN p.appartments a  INNER JOIN a.block b INNER JOIN b.employee e INNER JOIN e.company c WHERE p.paidOn != null GROUP BY c.companyId ORDER BY sum(p.payAmount) ";
            if (ascending)  {
                hql += " ASC";
            } else {
                hql += " DESC";
            }
            // String hql = "SELECT p FROM Paid p INNER JOIN Appartments a;";
            Query query = session.createQuery(hql);
            List<Object[]> _res = query.list();

            List<Pair<Company,Double>> res = new ArrayList<>();
            for (Object[] a: _res ) {
                res.add(new Pair<>((Company)a[0], (Double)a[1]));
            }

            return res;
        }
    }

    public static List<Pair<Company,Double>> MoneyToBeCollected() //Integer lessThan, Integer moreThan,String like)
    {
        Transaction transaction = null;
        try (Session session = SessionFactoryUtil.getSessionFactory().openSession()) {

            String hql = "SELECT c, sum(p.payAmount) FROM Paid p INNER JOIN p.appartments a  INNER JOIN a.block b INNER JOIN b.employee e INNER JOIN e.company c WHERE p.paidOn = null GROUP BY c.companyId";
            Query query = session.createQuery(hql);
            List<Object[]> _res = query.list();

            List<Pair<Company,Double>> res = new ArrayList<>();
            for (Object[] a: _res ) {
                res.add(new Pair<>((Company)a[0], (Double)a[1]));
            }

            return res;
        }
    }

    public static List<Pair<Company,Double>> MoneyCollected() //Integer lessThan, Integer moreThan,String like)
    {
        Transaction transaction = null;
        try (Session session = SessionFactoryUtil.getSessionFactory().openSession()) {

            String hql = "SELECT c, sum(p.payAmount) FROM Paid p INNER JOIN p.appartments a  INNER JOIN a.block b INNER JOIN b.employee e INNER JOIN e.company c WHERE p.paidOn != null GROUP BY c.companyId";
            Query query = session.createQuery(hql);
            List<Object[]> _res = query.list();

            List<Pair<Company,Double>> res = new ArrayList<>();
            for (Object[] a: _res ) {
                res.add(new Pair<>((Company)a[0], (Double)a[1]));
            }

            return res;
        }
    }


    // Delete a Company by its ID
    public static void delete(int id) {
        Transaction transaction = null;
        try (Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            Company company = session.get(Company.class, id);
            if (company != null) {
                session.delete(company);  // Delete the Company from the database
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
            CriteriaDelete<Company> delete = cb.createCriteriaDelete(Company.class);
            delete.from(Company.class);
            transaction = session.beginTransaction();
            session.createQuery(delete).executeUpdate();
            session.createNativeQuery("ALTER TABLE mydb.Company AUTO_INCREMENT = 1").executeUpdate();
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
        }
    }
}
