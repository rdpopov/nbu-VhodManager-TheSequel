package org.example.entity;

import javax.persistence.*;

@Entity
@Table(name = "Pets")
public class Pets {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idPets", nullable = false, unique = true)
    private Integer idPets;

    @ManyToOne
    @JoinColumn(
        name = "Appartments_ApptID", 
        referencedColumnName = "ApptID", 
        nullable = false,
        foreignKey = @ForeignKey(name = "fk_Pets_Appartments1")
    )
    private Appartments appartments;


    public Pets (Appartments appartment) {
        this.appartments = appartment;
    }
    // Getters and Setters
    public Integer getIdPets() {
        return idPets;
    }

    public void setIdPets(Integer idPets) {
        this.idPets = idPets;
    }

    public Appartments getAppartments() {
        return appartments;
    }

    public void setAppartments(Appartments appartments) {
        this.appartments = appartments;
    }

    @Override
    public String toString() {
        return "Pets{" +
                "idPets=" + idPets +
                ", appartments=" + appartments +
                '}';
    }
}
