package org.example.entity;

import javax.persistence.*;

import org.example.utils.VladoRandoma;

@Entity
@Table(name = "Company", schema = "mydb", uniqueConstraints = {
    @UniqueConstraint(columnNames = "CompanyID")
})
public class Company {

    @Id
    @Column(name = "CompanyID", nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    public int companyId;

    @Column(name = "CompanyName", nullable = false, length = 45)
    private String companyName;

    @Column(name = "CompanyAdress", nullable = false, length = 45)
    private String companyAddress;

    // Getters and Setters
    public int getCompanyId() {
        return companyId;
    }

    public void setCompanyId(int companyId) {
        this.companyId = companyId;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getCompanyAddress() {
        return companyAddress;
    }

    public void setCompanyAddress(String companyAddress) {
        this.companyAddress = companyAddress;
    }

   public Company() {
        this.companyName = VladoRandoma.generateName(); // "this" refers to the current instance
        this.companyAddress = VladoRandoma.generateAdress(); // "this" refers to the current instance
    }

   public Company(String name,String companyAddress) {
        this.companyName = name; // "this" refers to the current instance
        this.companyAddress = name; // "this" refers to the current instance
    }

    // Optional: toString() method for debugging
    @Override
    public String toString() {
        return "Company{" +
                "companyId=" + companyId +
                ", companyName='" + companyName + '\'' +
                ", companyAddress='" + companyAddress + '\'' +
                '}';
    }
}
