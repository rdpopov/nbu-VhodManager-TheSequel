package org.example.entity;

import javax.persistence.*;

@Entity
@Table(name = "Tax", schema = "mydb")
public class Tax {

    @Id
    @Column(name = "TaxId", nullable = false)
    private int taxId;

    @Column(name = "TaxPerAdult")
    private Double taxPerAdult;

    @Column(name = "PerChild")
    private Double perChild;

    @Column(name = "TaxArea")
    private Double taxArea;

    @Column(name = "TaxPet")
    private Double taxPet;

    @Column(name = "TaxFlat")
    private Double taxFlat;

    @Column(name = "TaxRepair")
    private Double taxRepair;

    // Getters and Setters
    public int getTaxId() {
        return taxId;
    }

    public void setTaxId(int taxId) {
        this.taxId = taxId;
    }

    public Double getTaxPerAdult() {
        return taxPerAdult;
    }

    public void setTaxPerAdult(Double taxPerAdult) {
        this.taxPerAdult = taxPerAdult;
    }

    public Double getPerChild() {
        return perChild;
    }

    public void setPerChild(Double perChild) {
        this.perChild = perChild;
    }

    public Double getTaxArea() {
        return taxArea;
    }

    public void setTaxArea(Double taxArea) {
        this.taxArea = taxArea;
    }

    public Double getTaxPet() {
        return taxPet;
    }

    public void setTaxPet(Double taxPet) {
        this.taxPet = taxPet;
    }

    public Double getTaxFlat() {
        return taxFlat;
    }

    public void setTaxFlat(Double taxFlat) {
        this.taxFlat = taxFlat;
    }

    public Double getTaxRepair() {
        return taxRepair;
    }

    public void setTaxRepair(Double taxRepair) {
        this.taxRepair = taxRepair;
    }

    // Optional: toString() method for debugging
    @Override
    public String toString() {
        return "Tax{" +
                "taxId=" + taxId +
                ", taxPerAdult=" + taxPerAdult +
                ", perChild=" + perChild +
                ", taxArea=" + taxArea +
                ", taxPet=" + taxPet +
                ", taxFlat=" + taxFlat +
                ", taxRepair=" + taxRepair +
                '}';
    }
}
