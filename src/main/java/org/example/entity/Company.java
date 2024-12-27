package org.example.entity;

import javax.persistence.*;

@Entity
@Table(name = "Company", schema = "mydb", uniqueConstraints = {
    @UniqueConstraint(columnNames = "CompanyID")
})
public class Company {

    @Id
    @Column(name = "CompanyID", nullable = false)
    private int companyId;

    @Column(name = "CompanyName", nullable = false, length = 45)
    private String companyName;

    @Column(name = "CompanyAdress", length = 45)
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
