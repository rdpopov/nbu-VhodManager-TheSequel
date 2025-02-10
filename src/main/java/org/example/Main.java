package org.example;

import org.example.configuration.SessionFactoryUtil;

import org.example.dao.*;
import org.example.entity.*;
import org.example.utils.VladoRandoma;

// import org.example.utils.VladoRandoma;
// import java.time.LocalDate;

public class Main {
    public static void clear_all() {
        SessionFactoryUtil.getSessionFactory().openSession();
        PaidDao.clear();
        InhabitantsDao.clear();
        PetsDao.clear();
        AppartmentsDao.clear();
        OwnersDao.clear();

        BlocksDao.clear();

        TaxDao.clear();

        EmployeeDao.clear();
        CompanyDao.clear();
    }

    public static void seed_db() {
        for (int p=0;p< 2;p++) {
            Company c = new Company();
            CompanyDao.save(c);
            for (int i = 0; i < 5; i++) {
                Employee e = new Employee(c);
                EmployeeDao.save(e);
                for (int j = 0; j < 5; j++) {
                    Tax t = new Tax();
                    TaxDao.save(t);
                    Blocks b = new Blocks(t,e);
                    BlocksDao.save(b);
                    int upper =  5;
                    // int upper = VladoRandoma.randomInt(10) + 5;
                    for (int k = 0; k < upper ; k++) {
                        Owners o = new Owners();
                        OwnersDao.save(o);
                        Appartments a = new Appartments(o,b);
                        AppartmentsDao.save(a);
                        int u2 = VladoRandoma.randomInt(5) + 1;

                        if (VladoRandoma.randomInt(10) <=2) {
                            Pets d = new Pets(a);
                            PetsDao.save(d);
                        };
                        for (int l = 0; l < u2; l++) {
                            Inhabitants in = new Inhabitants(a);
                            InhabitantsDao.save(in);
                        }
                    }
                }
            }
        }
        PaidDao.generatePayInfoForThisMonth();
        PaidDao.payRandomTransactions();
    }

    public static void main(String[] args) {
        // XXX: Demo INIT - do this once at start at least
        // clear_all();
        // seed_db();

        Filtering();
        GeneralisationFunctions();

        // // XXX: Get paid data to a file
        // PaidDao.ExportPaymentToCsv("hello.csv");
    }

    public static void DeleteFirstEmployees() {
        SessionFactoryUtil.getSessionFactory().openSession();
        System.out.println("ROSKO 1 delete the first emploee");
        EmployeeDao.deleteById(EmployeeDao.findAll().get(0).empId);
        EmployeeDao.sortByManagedBlocks(false).stream().forEach(a -> System.out.println(a));
    }

    public static String henlo() {
        return "henlo" ;
    }

    public static void Filtering() {
        // XXX: Filter companies on income
        CompanyDao.filterCompaniesOnAllIncome(false).stream().forEach(a -> System.out.println(a));
        System.out.println("ROSKO");

        // XXX: Filter employees on name
        EmployeeDao.sortByName(false).stream().forEach(a -> System.out.println(a));
        System.out.println("ROSKO");

        // XXX: Filter employees on managed blocks
        EmployeeDao.sortByManagedBlocks(false).stream().forEach(a -> System.out.println(a));
        System.out.println("ROSKO");

        // XXX: Filter Inhabitants on years old
        InhabitantsDao.sortYears(false).stream().forEach(a -> System.out.println(a));
        System.out.println("ROSKO");

        // XXX: Filter Inhabitants on name
        InhabitantsDao.filterNames("%a").stream().forEach(a -> System.out.println(a));
        System.out.println("ROSKO");
    }

    public static void GeneralisationFunctions() {
        // XXX: Appartments in a building 
        EmployeeDao.managedBlocksForCopmany(CompanyDao.findAll().get(0))
            .stream().forEach(a -> System.out.println(a)); //Integer lessThan, Integer moreThan,String like)
        System.out.println("ROSKO");

        // XXX: Appartments in a building 
        AppartmentsDao.getAppartmentsInBlockCount(BlocksDao.findAll().get(0))
            .stream().forEach(a -> System.out.println(a));
        System.out.println("ROSKO");

        // XXX: People in one block
        InhabitantsDao.getInhabitantsIn(BlocksDao.findAll().get(0))
            .stream().forEach(a -> System.out.println(a));
        System.out.println("ROSKO");

        // XXX: Money collected by employee
        EmployeeDao.MoneyCollected()
            .stream().forEach(a -> System.out.println(a));
        System.out.println("ROSKO");

        // XXX: Money to be collected by employee
        EmployeeDao.MoneyToBeCollected()
            .stream().forEach(a -> System.out.println(a));
        System.out.println("ROSKO");

        // XXX: Money to be collected by employee
        BlocksDao.MoneyCollected()
            .stream().forEach(a -> System.out.println(a));
        System.out.println("ROSKO");
    }
}
