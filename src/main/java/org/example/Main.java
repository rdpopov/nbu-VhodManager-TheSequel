package org.example;

import org.example.configuration.SessionFactoryUtil;
import org.example.dao.CompanyDao;
import org.example.entity.Company;

import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {

        System.out.println("Hello world!");
        // SessionFactoryUtil.getSessionFactory().openSession();


        // Company company = new Company("Nestle",
        //         LocalDate.of(2005, 10, 10), 6000);

       // // CompanyDao.saveCompany(company);

        // Company company1 = CompanyDao.getCompanyById(11);
        // System.out.println(company1);

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
}
