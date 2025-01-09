package org.example.dao;

import org.hibernate.Session;
import org.example.entity.Blocks;
import org.example.entity.Company;
import org.example.entity.Employee;
import org.example.configuration.SessionFactoryUtil;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import com.mysql.cj.conf.ConnectionUrlParser.Pair;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.persistence.Tuple;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaDelete;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Root;

public class EmployeeDao { 

    // Save a new Employee
    public static void save(Employee employee) {
        Transaction transaction = null;
        try (Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.save(employee);  // Save the Employee object to the database
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();  // Rollback in case of error
            e.printStackTrace();
        }
    }

    // Find an Employee by ID
    public static Employee findById(int empId) {
        try (Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            return session.get(Employee.class, empId);  // Retrieve Employee object by ID
        }
    }

    public static List<Employee> findByCompanyId(int companyid) {
        try (Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            CriteriaBuilder cb = SessionFactoryUtil.getSessionFactory().getCriteriaBuilder();
            CriteriaQuery<Employee> query = cb.createQuery(Employee.class);
            Root<Employee> root = query.from(Employee.class);

            query.select(root)
                .where(cb.equal(root.get("company"), companyid));
            return session.createQuery(query).getResultList();
        }
    }

    // Find all Employees
    public static List<Employee> findAll() {
        try (Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            Query<Employee> query = session.createQuery("from Employee", Employee.class);
            return query.list();  // Return all Employees in the database
        }
    }
    // todo: add a thing for load balanicing the Employees
    // Update an existing Employee
    public static void update(Employee employee) {
        Transaction transaction = null;
        try (Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.update(employee);  // Update the Employee object in the database
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();  // Rollback in case of error
            e.printStackTrace();
        }
    }

    // Delete an Employee by ID
    public static void deleteById(int empId) {
        Transaction transaction = null;

        try (Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            Employee toDelete = session.get(Employee.class, empId);
            if (toDelete != null) {
                Company c = toDelete.getCompany();
                transaction = session.beginTransaction();

                String hql = "SELECT bl FROM Blocks bl INNER JOIN bl.employee em WHERE em.company.companyId = :cmpid";
                Query query = session.createQuery(hql);
                query.setParameter("cmpid", c.getCompanyId());

                List<Blocks> res = query.list();
                List<Blocks> ofThisEmployee = res
                    .stream()
                    .filter(bl -> bl.getEmployee().getEmpId() == empId).collect(Collectors.toList());

                List<Blocks> ofOther = res
                    .stream()
                    .filter(bl -> bl.getEmployee().getEmpId() != empId).collect(Collectors.toList());

                Map<Integer,Integer> blocks = new HashMap<>();
                for(Blocks oth: ofOther ) {
                    Integer current = oth.getEmployee().getEmpId();
                    if (blocks.get(current) == null) {
                        blocks.put(current, 0);
                    }
                    blocks.put(current,blocks.get(current) +1);
                }

                for(Blocks oft: ofThisEmployee )
                {
                    Integer minVal = Collections.min(blocks.values());
                    Integer key = 0;
                    for(Integer k: blocks.keySet()) {
                        if(minVal.equals(blocks.get(k))) {
                            key = k;
                            break;
                        }
                    }
                    blocks.put(key,blocks.get(key) +1);
                    oft.setEmployee(session.get(Employee.class, key));
                    session.update(oft);
                }

                session.delete(toDelete);
                System.out.println("Deleted");
                transaction.commit();
            } else {
                System.out.println("Already deleted");
            }

        } catch (Exception e) {
            if (transaction != null) transaction.rollback();  // Rollback in case of error
            e.printStackTrace();
        }
    }

    public static List<Employee> sortByName(Boolean ascending)
    {
        Transaction transaction = null;
        try (Session session = SessionFactoryUtil.getSessionFactory().openSession()) {

            String hql = "SELECT e FROM Employee e ORDER BY e.empFirstname ";
            if (ascending)  {
                hql += " ASC";
            } else {
                hql += " DESC";
            }
            // String hql = "SELECT p FROM Paid p INNER JOIN Appartments a;";
            Query query = session.createQuery(hql);
            List<Employee> _res = query.list();
            return _res;
        }
    }

    public static List<Pair<Employee,Blocks>> managedBlocksForCopmany(Company c) //Integer lessThan, Integer moreThan,String like)
    {
        Transaction transaction = null;
        try (Session session = SessionFactoryUtil.getSessionFactory().openSession()) {

            String hql = "SELECT e, b FROM Blocks b INNER JOIN b.employee e WHERE e.company.companyId = :compid";

            Query query = session.createQuery(hql);
            query.setParameter("compid",c.companyId);
            List<Object[]> _res = query.list();

            List<Pair<Employee,Blocks>> res = new ArrayList<>();
            for (Object[] a: _res ) {
                res.add(new Pair<>((Employee)a[0], (Blocks)a[1]));
            }

            return res;
        }
    }

    public static List<Pair<Employee,Long>> managedBlocksCount(Company c) //Integer lessThan, Integer moreThan,String like)
    {
        Transaction transaction = null;
        try (Session session = SessionFactoryUtil.getSessionFactory().openSession()) {

            String hql = "SELECT e, count(b) FROM Blocks b INNER JOIN b.employee e WHERE e.company.companyId = :compid GROUP BY e.empId ";

            Query query = session.createQuery(hql);
            query.setParameter("compid",c.companyId);
            List<Object[]> _res = query.list();

            List<Pair<Employee,Long>> res = new ArrayList<>();
            for (Object[] a: _res ) {
                res.add(new Pair<>((Employee)a[0], (Long)a[1]));
            }

            return res;
        }
    }

    public static List<Pair<Employee,Double>> MoneyToBeCollected() //Integer lessThan, Integer moreThan,String like)
    {
        Transaction transaction = null;
        try (Session session = SessionFactoryUtil.getSessionFactory().openSession()) {

            // String hql = "SELECT c.companyId FROM Paid p INNER JOIN Appartments a INNER JOIN Blocks b INNER JOIN Employee e INNER JOIN Company c GROUP BY c.companyId" ;
            // String hql = "SELECT c.companyId FROM Paid p INNER JOIN Company c WHERE p.appartments.block.employee.company.companyId = c.companyId GROUP BY c.companyId";
            String hql = "SELECT e, sum(p.payAmount) FROM Paid p INNER JOIN p.appartments a  INNER JOIN a.block b INNER JOIN b.employee e WHERE p.paidOn = null GROUP BY e.empId";
            // String hql = "SELECT p FROM Paid p INNER JOIN Appartments a;";
            Query query = session.createQuery(hql);
            List<Object[]> _res = query.list();

            List<Pair<Employee,Double>> res = new ArrayList<>();
            for (Object[] a: _res ) {
                res.add(new Pair<>((Employee)a[0], (Double)a[1]));
            }

            return res;
        }
    }

    public static List<Pair<Employee,Double>> MoneyCollected() //Integer lessThan, Integer moreThan,String like)
    {
        Transaction transaction = null;
        try (Session session = SessionFactoryUtil.getSessionFactory().openSession()) {

            // String hql = "SELECT c.companyId FROM Paid p INNER JOIN Appartments a INNER JOIN Blocks b INNER JOIN Employee e INNER JOIN Company c GROUP BY c.companyId" ;
            // String hql = "SELECT c.companyId FROM Paid p INNER JOIN Company c WHERE p.appartments.block.employee.company.companyId = c.companyId GROUP BY c.companyId";
            String hql = "SELECT e, sum(p.payAmount) FROM Paid p INNER JOIN p.appartments a  INNER JOIN a.block b INNER JOIN b.employee e WHERE p.paidOn != null GROUP BY e.empId";
            // String hql = "SELECT p FROM Paid p INNER JOIN Appartments a;";
            Query query = session.createQuery(hql);
            List<Object[]> _res = query.list();

            List<Pair<Employee,Double>> res = new ArrayList<>();
            for (Object[] a: _res ) {
                res.add(new Pair<>((Employee)a[0], (Double)a[1]));
            }
            return res;
        }
    }

    public static List<Pair<Employee,Long>> sortByManagedBlocks(Boolean ascending) //Integer lessThan, Integer moreThan,String like)
    {
        Transaction transaction = null;
        try (Session session = SessionFactoryUtil.getSessionFactory().openSession()) {

            String hql = "SELECT e, count(b) FROM Blocks b INNER JOIN b.employee e GROUP BY e.empId ORDER BY count(b) ";
            if (ascending)  {
                hql += " ASC";
            } else {
                hql += " DESC";
            }
            Query query = session.createQuery(hql);
            List<Object[]> _res = query.list();

            List<Pair<Employee,Long>> res = new ArrayList<>();
            for (Object[] a: _res ) {
                res.add(new Pair<>((Employee)a[0], (Long)a[1]));
            }

            return res;
        }
    }

    public static void clear() {
        Transaction transaction = null;

        try (Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            CriteriaBuilder cb = session.getCriteriaBuilder();
            CriteriaDelete<Employee> delete = cb.createCriteriaDelete(Employee.class);
            delete.from(Employee.class);
            transaction = session.beginTransaction();
            session.createQuery(delete).executeUpdate();
            session.createNativeQuery("ALTER TABLE mydb.Employee AUTO_INCREMENT = 1").executeUpdate();
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
        }
    }
}
