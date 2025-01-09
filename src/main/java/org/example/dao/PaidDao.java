package org.example.dao;

import org.hibernate.Session;
import org.example.entity.Paid;
import org.example.utils.VladoRandoma;
import org.example.configuration.SessionFactoryUtil;
import org.hibernate.Transaction;
import org.hibernate.query.NativeQuery;
import org.hibernate.query.Query;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import javax.persistence.*;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaDelete;

public class PaidDao {

    // Save a new Paid entry
    public static void save(Paid paid) {
        Transaction transaction = null;
        try (Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.save(paid);  // Save the Paid object to the database
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();  // Rollback in case of error
            e.printStackTrace();
        }
    }

    // Find a Paid entry by ID
    public static Paid findById(int paidId) {
        try (Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            return session.get(Paid.class, paidId);  // Retrieve Paid object by ID
        }
    }

    // Find all Paid entries
    public static List<Paid> findAll() {
        try (Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            Query<Paid> query = session.createQuery("from Paid", Paid.class);
            return query.list();  // Return all Paid entries in the database
        }
    }

    public static void payRandomTransactions() {
        Transaction transaction = null;
        try (Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            Query<Paid> query = session.createQuery("select p from Paid p where p.paidOn = null", Paid.class);
            List<Paid> res = query.getResultList();
            for (Paid p : res ) {
                if (VladoRandoma.randomInt(10) <=2) {
                    p.Pay();
                    session.update(p);
                };
            }
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();  // Rollback in case of error
            e.printStackTrace();
        }
    }
    // Update an existing Paid entry
    public static void update(Paid paid) {
        Transaction transaction = null;
        try (Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.update(paid);  // Update the Paid object in the database
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();  // Rollback in case of error
            e.printStackTrace();
        }
    }

    private static List<Object[]> getPagedResultsWithParams(Session session,String sqlQuery, int pageNumber, int pageSize) {
        int offset = (pageNumber - 1) * pageSize;

        NativeQuery<Object[]> query = session.createNativeQuery(sqlQuery);
        query.setFirstResult(offset); // Set pagination offset
        query.setMaxResults(pageSize); // Set pagination limit

        return query.list();
    }

    public static void generatePayInfoForThisMonth() {
        Transaction transaction = null;
        try (Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();

            // Query query = session.createQuery(hql);
            String amounts_per_appartment = 
                "with people as (select " +
                "a.ApptID, " +
                "sum(if(timestampdiff(YEAR, i.InhDateOfBirth,NOW()) > 7,t.TaxPerAdult,t.PerChild)) as si " +
                    "from mydb.Blocks b " +
                    "join mydb.Appartments a on b.BlockID = a.ApptBlockID " +
                    "join mydb.Tax t on b.TaxTaxID = t.TaxID " +
                    "join mydb.Inhabitants i on a.ApptID = i.AppartmentsApptID " +
                    "group by a.ApptID ), " +
                "pets as ( " +
                        "select " +
                        "a.ApptID, " +
                        "sum(t.TaxPet) as sp " +
                        "from mydb.Blocks b " +
                        "join mydb.Appartments a on b.BlockID = a.ApptBlockID " +
                        "join mydb.Tax t on b.TaxTaxID = t.TaxID " +
                        "join mydb.Pets p on a.ApptID = p.Appartments_ApptID " +
                        "group by a.ApptID " +
                        ") " +
                    "select a.ApptID, t.TaxID, " +
                "t.TaxArea * a.ApptArea +  " +
                    "people.si +  " +
                    "pets.sp +  " +
                    "t.TaxFlat +  " +
                    "t.TaxRepair as tax " +
                    "from mydb.Blocks b " +
                    "join mydb.Appartments a on b.BlockID = a.ApptBlockID " +
                    "join mydb.Tax t on b.TaxTaxID = t.TaxID " +
                    "join people on a.ApptID = people.ApptID " +
                    "join pets on a.ApptID = pets.ApptID " ;

            Integer PageSize = 50;
            Integer i  = 1;
            Boolean end=true;
            while(end) {
                List<Object[]> res =  getPagedResultsWithParams(session,amounts_per_appartment,i,PageSize);


                StringBuilder ins  = new StringBuilder("insert into mydb.Paid(TaxTaxID,AppartmentsApptID,PayAmount,PaidOn,PayTime) values ");
                i++;
                end = false;
                for (Object[] row : res) {
                    Long ApptID = ((Number) row[0]).longValue();
                    Long TaxId = ((Number) row[1]).longValue();
                    Double tax = ((Number) row[2]).doubleValue();
                    end = true;

                    ins.append(String.format("(%d,%d,%f,null,NOW()),", TaxId,ApptID,tax));
                    // System.out.println("Page " + i +  " " + ApptID + " " + TaxId + " " + tax);
                }
                if (end) {
                    ins.deleteCharAt(ins.length() - 1);
                    ins.append(";");
                    session.createNativeQuery(ins.toString()).executeUpdate();
                }
            }
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();  // Rollback in case of error
            e.printStackTrace();
        }
    }


    public static void ExportPaymentToCsv(String fname) {
        try( Session session = SessionFactoryUtil.getSessionFactory().openSession()){
            FileWriter myWriter = new FileWriter(fname);

            String hql = "SELECT c.companyId,c.companyName,e.empFirstname,e.empLastname,b.blockName,a.apptNumber, p.payAmount, p.paidOn FROM Paid p INNER JOIN p.appartments a  INNER JOIN a.block b INNER JOIN b.employee e INNER JOIN e.company c WHERE p.paidOn != null";
            Query query = session.createQuery(hql);
            List<Object[]> res = query.list();

            String header = "CompanyID,CompanyName,EmployeeFirstName,EmployeeLastname,BlockName,Appartment,TaxAmount;\n";
            myWriter.write(header);
            for (Object[] row : res) {
                String r = String.format("%d,%s,%s,%s,%s,%s,%f;\n", row[0], row[1],row[2],row[3],row[4],row[5],row[6]);
                myWriter.write(r);
                // System.out.println(r);
            }

            myWriter.close();
            System.out.println("Successfully wrote to the file.");
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }


    // Delete a Paid entry by ID
    public static void delete(int paidId) {
        Transaction transaction = null;
        try (Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            Paid paid = session.get(Paid.class, paidId);
            if (paid != null) {
                session.delete(paid);  // Delete the Paid object from the database
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
            CriteriaDelete<Paid> delete = cb.createCriteriaDelete(Paid.class);
            delete.from(Paid.class);
            transaction = session.beginTransaction();
            session.createQuery(delete).executeUpdate();
            session.createNativeQuery("ALTER TABLE mydb.Paid AUTO_INCREMENT = 1").executeUpdate();
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
        }
    }
}
