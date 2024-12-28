package org.example.entity;

import javax.persistence.*;

@Entity
@Table(name = "Tax", schema = "mydb")
public class Tax {

    @Id
    @Column(name = "TaxId", nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int taxId;

    @Column(name = "TaxPerAdult",nullable = false)
    private Double taxPerAdult;

    @Column(name = "PerChild",nullable = false)
    private Double perChild;

    @Column(name = "TaxArea",nullable = false)
    private Double taxArea;

    @Column(name = "TaxPet",nullable = false)
    private Double taxPet;

    @Column(name = "TaxFlat",nullable = false)
    private Double taxFlat;

    @Column(name = "TaxRepair",nullable = false)
    private Double taxRepair;

    public Tax() {
        this.taxId=0;
        this.taxPerAdult=0.0;
        this.perChild=0.0;
        this.taxArea=0.0;
        this.taxPet=0.0;
        this.taxFlat=0.0;
        this.taxRepair=0.0;
    }

    public Tax(Double taxPerAdult, Double perChild, Double taxArea, Double taxPet, Double taxFlat, Double taxRepair){
        this.taxPerAdult =taxPerAdult;
        this.perChild =perChild;
        this.taxArea =taxArea;
        this.taxPet =taxPet;
        this.taxFlat =taxFlat;
        this.taxRepair =taxRepair;
    }

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
