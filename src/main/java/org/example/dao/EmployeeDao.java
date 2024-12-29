package org.example.dao;

import org.hibernate.Session;
import org.example.entity.Employee;
import org.example.configuration.SessionFactoryUtil;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import java.util.List;

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

    // Find all Employees
    public static List<Employee> findAll() {
        try (Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            Query<Employee> query = session.createQuery("from Employee", Employee.class);
            return query.list();  // Return all Employees in the database
        }
    }

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
    public static void delete(int empId) {
        Transaction transaction = null;
        try (Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            Employee employee = session.get(Employee.class, empId);
            if (employee != null) {
                session.delete(employee);  // Delete the Employee from the database
            }
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();  // Rollback in case of error
            e.printStackTrace();
        }
    }
}
