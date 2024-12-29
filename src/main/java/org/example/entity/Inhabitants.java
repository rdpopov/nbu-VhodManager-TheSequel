package org.example.entity;

import javax.persistence.*;

import org.example.utils.VladoRandoma;

import java.time.Instant;
import java.util.Date;

@Entity
@Table(name = "Inhabitants", schema = "mydb")
public class Inhabitants {

    @Id
    @Column(name = "InhID", nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int inhId;

    @Column(name = "InhFirstname", nullable = false,length = 45)
    private String inhFirstname;

    @Column(name = "InhLastname",nullable = false, length = 45)
    private String inhLastname;

    @Column(name = "InhDateOfBirth",nullable = false)
    @Temporal(TemporalType.DATE)
    private Date inhDateOfBirth;

    @ManyToOne
    @JoinColumn(name = "AppartmentsApptID", nullable = false, referencedColumnName = "ApptID",
                foreignKey = @ForeignKey(name = "fk_Inhabitants_Appartments1"))
    private Appartments appartment;

    public Inhabitants () {
        this.inhFirstname="";
        this.inhLastname="";
        this.inhDateOfBirth = new Date();
        this.appartment=null;
    }

    public Inhabitants (Appartments appartment) {
        this.inhFirstname=VladoRandoma.generateName();
        this.inhLastname=VladoRandoma.generateName();
        this.inhDateOfBirth = VladoRandoma.generateDate();
        this.appartment=appartment;
    }

    public Inhabitants (String inhFirstname, String inhLastname, Date inhDateOfBirth, Appartments appartment) {
        this.inhFirstname=inhFirstname;
        this.inhLastname=inhLastname;
        this.inhDateOfBirth = inhDateOfBirth ;
        this.appartment=appartment;
    }

    // Getters and Setters
    public int getInhId() {
        return inhId;
    }

    public void setInhId(int inhId) {
        this.inhId = inhId;
    }

    public String getInhFirstname() {
        return inhFirstname;
    }

    public void setInhFirstname(String inhFirstname) {
        this.inhFirstname = inhFirstname;
    }

    public String getInhLastname() {
        return inhLastname;
    }

    public void setInhLastname(String inhLastname) {
        this.inhLastname = inhLastname;
    }

    public Date getInhDateOfBirth() {
        return inhDateOfBirth;
    }

    public void setInhDateOfBirth(Date inhDateOfBirth) {
        this.inhDateOfBirth = inhDateOfBirth;
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
        return "Inhabitants{" +
                "inhId=" + inhId +
                ", inhFirstname='" + inhFirstname + '\'' +
                ", inhLastname='" + inhLastname + '\'' +
                ", inhDateOfBirth=" + inhDateOfBirth +
                ", appartment=" + appartment +
                '}';
    }
}
