package org.example.entity;

import javax.persistence.*;

@Entity
@Table(name = "Employee", schema = "mydb", uniqueConstraints = {
    @UniqueConstraint(columnNames = "EmpID")
})
public class Employee {

    @Id
    @Column(name = "EmpID", nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int empId;

    @Column(name = "EmpFirstname", length = 45)
    private String empFirstname;

    @Column(name = "EmpLastname", length = 45)
    private String empLastname;

    @Column(name = "EmpEGN", length = 15)
    private String empEgn;

    @Column(name = "EmpTel", length = 15)
    private String empTel;

    @ManyToOne
    @JoinColumn(name = "CompanyCompanyID", nullable = false, referencedColumnName = "CompanyID",
    foreignKey = @ForeignKey(name = "fk_Employee_Company"))
    private Company company;

    public Employee () {
        this.empId=0;
        this.empFirstname="";
        this.empLastname="";
        this.empEgn="";
        this.empTel="";
        this.company=null;
    }

    public Employee (String empFirstname, String empLastname, String empEgn, String empTel, Company company){
        this.empFirstname=empFirstname;
        this.empLastname=empLastname;
        this.empEgn=empEgn;
        this.empTel=empTel;
        this.company=company;
    }

    // Getters and Setters
    public int getEmpId() {
        return empId;
    }

    public void setEmpId(int empId) {
        this.empId = empId;
    }

    public String getEmpFirstname() {
        return empFirstname;
    }

    public void setEmpFirstname(String empFirstname) {
        this.empFirstname = empFirstname;
    }

    public String getEmpLastname() {
        return empLastname;
    }

    public void setEmpLastname(String empLastname) {
        this.empLastname = empLastname;
    }

    public String getEmpEgn() {
        return empEgn;
    }

    public void setEmpEgn(String empEgn) {
        this.empEgn = empEgn;
    }

    public String getEmpTel() {
        return empTel;
    }

    public void setEmpTel(String empTel) {
        this.empTel = empTel;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    // Optional: toString() method for debugging
    @Override
    public String toString() {
        return "Employee{" +
                "empId=" + empId +
                ", empFirstname='" + empFirstname + '\'' +
                ", empLastname='" + empLastname + '\'' +
                ", empEgn='" + empEgn + '\'' +
                ", empTel='" + empTel + '\'' +
                ", company=" + company +
                '}';
    }
}
