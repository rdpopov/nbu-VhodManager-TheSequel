package org.example.entity;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "Inhabitants", schema = "mydb")
public class Inhabitants {

    @Id
    @Column(name = "InhID", nullable = false)
    private int inhId;

    @Column(name = "InhFirstname", length = 45)
    private String inhFirstname;

    @Column(name = "InhLastname", length = 45)
    private String inhLastname;

    @Column(name = "InhDateOfBirth")
    @Temporal(TemporalType.DATE)
    private Date inhDateOfBirth;

    @ManyToOne
    @JoinColumn(name = "Appartments_ApptID", nullable = false, referencedColumnName = "ApptID",
                foreignKey = @ForeignKey(name = "fk_Inhabitants_Appartments1"))
    private Appartments appartment;

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
