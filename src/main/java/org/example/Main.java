package org.example;

import org.example.configuration.SessionFactoryUtil;

import org.example.dao.*;
import org.example.entity.*;

import org.example.utils.VladoRandoma;

import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world!");
        // SessionFactoryUtil.getSessionFactory().openSession();
        // Company company = new Company("Asdf","ghj");
        // CompanyDao.save(company);
        //

        SessionFactoryUtil.getSessionFactory().openSession();
        Tax company = new Tax();
        TaxDao.save(company);

        // Company company1 = CompanyDao.getCompanyById(11);
        // System.out.println(company1);
        // for (Company all : CompanyDao.findAll()) {
        //     System.out.println(all);
        // }

        // company1.setName("Nestle Edited");
        // CompanyDao.updateCompany(company1);

        // System.out.println(CompanyDao.getCompanyById(company1.getId()));

        
        // CompanyDao.deleteCompany(company1);

        // CompanyDao.getCompanies()
        //         .stream()
        //         .forEach(System.out::println);









// //        Company company = new Company("DHL", LocalDate.of(2022,03,03),10000);
// //
// //        CompanyDao.createCompany(company);
// //
// //        CompanyDao.getCompanies()
// //                .stream()
// //                .forEach(System.out::println);
    }
    public static String henlo() {
        return "henlo" ;
    }
}
