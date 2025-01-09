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

        InhabitantsDao.clear();
        PetsDao.clear();
        AppartmentsDao.clear();
        OwnersDao.clear();

        BlocksDao.clear();

        PaidDao.clear();
        TaxDao.clear();

        EmployeeDao.clear();
        CompanyDao.clear();
    }

    public static void seed_db() {
        for (int p=0;p< 5;p++) {
            Company c = new Company();
            CompanyDao.save(c);
            for (int i = 0; i < 10; i++) {
                Employee e = new Employee(c);
                EmployeeDao.save(e);
                for (int j = 0; j < 6; j++) {
                    Tax t = new Tax();
                    TaxDao.save(t);
                    Blocks b = new Blocks(t,e);
                    BlocksDao.save(b);
                    int upper = VladoRandoma.randomInt(10) + 5;
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
    }

    public static void main(String[] args) {
        System.out.println("Hello world!");

        // clear_all();
        // seed_db();

        // XXX: This is for remving an employee and then dividing the blocks to other people
        // SessionFactoryUtil.getSessionFactory().openSession();
        // EmployeeDao.deleteById(4468);
        //

        // CompanyDao.filterCompaniesOnAllIncome(false).stream().forEach(a -> System.out.println(a));
        // EmployeeDao.sortByName(false).stream().forEach(a -> System.out.println(a));
        // EmployeeDao.sortByManagedBlocks(false).stream().forEach(a -> System.out.println(a));

        // InhabitantsDao.sortYears(false).stream().forEach(a -> System.out.println(a));
        // InhabitantsDao.filterNames("%a").stream().forEach(a -> System.out.println(a));
        
        // EmployeeDao.managedBlocksCount(CompanyDao.findAll().get(0)) .stream().forEach(a -> System.out.println(a)); //Integer lessThan, Integer moreThan,String like)
                                                           //
        // EmployeeDao.managedBlocksForCopmany(CompanyDao.findAll().get(0))
        //     .stream().forEach(a -> System.out.println(a)); //Integer lessThan, Integer moreThan,String like)
 
        // AppartmentsDao.getAppartmentsInBlock(BlocksDao.findAll().get(0))
        //     .stream().forEach(a -> System.out.println(a));

        // AppartmentsDao.getAppartmentsInBlockCount(BlocksDao.findAll().get(0))
        //     .stream().forEach(a -> System.out.println(a));

        // InhabitantsDao.getInhabitantsIn(BlocksDao.findAll().get(0))
        //     .stream().forEach(a -> System.out.println(a));

        // InhabitantsDao.getInhabitantsCountIn(BlocksDao.findAll().get(0))
        //     .stream().forEach(a -> System.out.println(a));
        // BlocksDao.MoneyToBeCollected()
        //     .stream().forEach(a -> System.out.println(a));

        // BlocksDao.MoneyCollected()
        //     .stream().forEach(a -> System.out.println(a));

        PaidDao.ExportPaymentToCsv("hello.csv");
        // PaidDao.generatePayInfoForThisMonth();
        // PaidDao.payRandomTransactions();
    }
    public static String henlo() {
        return "henlo" ;
    }
}
