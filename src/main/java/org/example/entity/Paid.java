package org.example.entity;


import org.example.entity.Appartments;
import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "Paid")
public class Paid {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "PaidID", nullable = false, unique = true)
    private Integer paidID;

    @Column(name = "PayAmount", nullable = false)
    private Double payAmount;

    @Column(name = "PaidOn")
    private Date paidOn;

    @Column(name = "PayTime", nullable = false)
    private Date payTime;

    @ManyToOne
    @JoinColumn(
        name = "TaxTaxId",
        referencedColumnName = "TaxId",
        nullable = false,
        foreignKey = @ForeignKey(name = "fk_Paid_Tax1")
    )
    private Tax tax;

    @ManyToOne
    @JoinColumn(
        name = "AppartmentsApptID",
        referencedColumnName = "ApptID",
        nullable = false,
        foreignKey = @ForeignKey(name = "fk_Paid_Appartments1")
    )
    private Appartments appartments;


    public Paid() {
        this.paidID=0;
        this.tax=null;
        this.appartments=null;
        this.payAmount = null;
        this.paidOn = null;
        this.payTime = null;
    }

    public Paid(Tax tax, Appartments appartment,Double payAmount,Date payTime) {
        this.tax=tax;
        this.appartments =appartment;
        this.payAmount = payAmount;
        this.payTime = payTime;
    }

    public void Pay() {
        this.paidOn = new Date();
    }

    // Getters and Setters
    public Integer getPaidID() {
        return paidID;
    }

    public void setPaidID(Integer paidID) {
        this.paidID = paidID;
    }

    public Double getPayAmount() {
        return payAmount;
    }

    public void setPayAmount(Double payAmount) {
        this.payAmount = payAmount;
    }

    public Date getPaidOn() {
        return paidOn;
    }

    public void setPaidOn(Date paidOn) {
        this.paidOn = paidOn;
    }

    public Date getPayTime() {
        return payTime;
    }

    public void setPayTime(Date payTime) {
        this.payTime = payTime;
    }

    public Tax getTax() {
        return tax;
    }

    public void setTax(Tax tax) {
        this.tax = tax;
    }

    public Appartments getAppartments() {
        return appartments;
    }

    public void setAppartments(Appartments appartments) {
        this.appartments = appartments;
    }

    @Override
    public String toString() {
        return "Paid{" +
                "paidID=" + paidID +
                ", payAmount=" + payAmount +
                ", paidOn=" + paidOn +
                ", payTime=" + payTime +
                ", tax=" + (tax != null ? tax.getTaxId() : "null") +
                ", appartments=" + (appartments != null ? appartments.getApptId() : "null") +
                '}';
    }
}
