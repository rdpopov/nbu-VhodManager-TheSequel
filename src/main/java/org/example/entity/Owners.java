package org.example.entity;

import javax.persistence.*;

import org.example.utils.VladoRandoma;

@Entity
@Table(name = "Owners", schema = "mydb")
public class Owners {

    @Id
    @Column(name = "OwnerID", nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int ownerId;

    @Column(name = "OwnerFirstname", length = 45,nullable = false)
    private String ownerFirstname;

    @Column(name = "OwnerLastname", length = 45,nullable = false)
    private String ownerLastname;

    @Column(name = "OwnerPhone", length = 15,nullable = false)
    private String ownerPhone;

    @Column(name = "OwnerEGN", length = 15,nullable = false)
    private String ownerEgn;

    // Getters and Setters
    public int getOwnerId() {
        return ownerId;
    }

    public Owners() {
        this.ownerFirstname=VladoRandoma.generateName();
        this.ownerLastname=VladoRandoma.generateName();
        this.ownerPhone=VladoRandoma.generateStringNumbber(12);
        this.ownerEgn=VladoRandoma.generateStringNumbber(10);
    }

    public Owners(String ownerFirstname, String ownerLastname, String ownerPhone, String ownerEgn) {
        this.ownerFirstname = ownerFirstname;
        this.ownerLastname = ownerLastname;
        this.ownerPhone = ownerPhone;
        this.ownerEgn = ownerEgn;
    }

    public void setOwnerId(int ownerId) {
        this.ownerId = ownerId;
    }

    public String getOwnerFirstname() {
        return ownerFirstname;
    }

    public void setOwnerFirstname(String ownerFirstname) {
        this.ownerFirstname = ownerFirstname;
    }

    public String getOwnerLastname() {
        return ownerLastname;
    }

    public void setOwnerLastname(String ownerLastname) {
        this.ownerLastname = ownerLastname;
    }

    public String getOwnerPhone() {
        return ownerPhone;
    }

    public void setOwnerPhone(String ownerPhone) {
        this.ownerPhone = ownerPhone;
    }

    public String getOwnerEgn() {
        return ownerEgn;
    }

    public void setOwnerEgn(String ownerEgn) {
        this.ownerEgn = ownerEgn;
    }

    // Optional: toString() method for debugging
    @Override
    public String toString() {
        return "Owners{" +
                "ownerId=" + ownerId +
                ", ownerFirstname='" + ownerFirstname + '\'' +
                ", ownerLastname='" + ownerLastname + '\'' +
                ", ownerPhone='" + ownerPhone + '\'' +
                ", ownerEgn='" + ownerEgn + '\'' +
                '}';
    }
}
