package org.example.entity;

import javax.persistence.*;


@Entity
@Table(name = "Paid", schema = "mydb")
public class Paid {

    @Id
    @Column(name = "PaidID", nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int paidId;

    @ManyToOne
    @JoinColumn(name = "TaxTaxId", nullable = false, referencedColumnName = "TaxId",
                foreignKey = @ForeignKey(name = "fk_Paid_Tax1"))
    private Tax tax;

    @ManyToOne
    @JoinColumn(name = "AppartmentsApptID", nullable = false, referencedColumnName = "ApptID",
                foreignKey = @ForeignKey(name = "fk_Paid_Appartments1"))
    private Appartments appartment;

    public Paid() {
        this.paidId=0;
        this.tax=null;
        this.appartment=null;
    }

    public Paid(Tax tax, Appartments appartment) {
        this.tax=tax;
        this.appartment=appartment;
    }

    // Getters and Setters
    public int getPaidId() {
        return paidId;
    }

    public void setPaidId(int paidId) {
        this.paidId = paidId;
    }

    public Tax getTax() {
        return tax;
    }

    public void setTax(Tax tax) {
        this.tax = tax;
    }

    public Appartments getAppartment() {
        return appartment;
    }

    public void setAppartment(Appartments appartment) {
        this.appartment = appartment;
    }

    // Optional: toString() method for debugging
    @Override
    public String toString() {
        return "Paid{" +
                "paidId=" + paidId +
                ", tax=" + tax +
                ", appartment=" + appartment +
                '}';
    }
}
