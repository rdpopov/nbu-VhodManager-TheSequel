package org.example.entity;

import javax.persistence.*;

@Entity
@Table(name = "Appartments", schema = "mydb")
public class Appartments {

    @Id
    @Column(name = "ApptID", nullable = false)
    private int apptId;

    @Column(name = "ApptFloor")
    private Integer apptFloor;

    @Column(name = "ApptArea")
    private Double apptArea;

    @Column(name = "ApptNumber", length = 10)
    private String apptNumber;

    @ManyToOne
    @JoinColumn(name = "OwnersOwnerID", nullable = false, referencedColumnName = "OwnerID",
                foreignKey = @ForeignKey(name = "fk_Appartments_Owners1"))
    private Owners owner;

    @ManyToOne
    @JoinColumn(name = "ApptBlockID", nullable = false, referencedColumnName = "BlockID",
                foreignKey = @ForeignKey(name = "fk_Appartments_Blocks1"))
    private Blocks block;

    // Getters and Setters
    public int getApptId() {
        return apptId;
    }

    public void setApptId(int apptId) {
        this.apptId = apptId;
    }

    public Integer getApptFloor() {
        return apptFloor;
    }

    public void setApptFloor(Integer apptFloor) {
        this.apptFloor = apptFloor;
    }

    public Double getApptArea() {
        return apptArea;
    }

    public void setApptArea(Double apptArea) {
        this.apptArea = apptArea;
    }

    public String getApptNumber() {
        return apptNumber;
    }

    public void setApptNumber(String apptNumber) {
        this.apptNumber = apptNumber;
    }

    public Owners getOwner() {
        return owner;
    }

    public void setOwner(Owners owner) {
        this.owner = owner;
    }

    public Blocks getBlock() {
        return block;
    }

    public void setBlock(Blocks block) {
        this.block = block;
    }

    // Optional: toString() method for debugging
    @Override
    public String toString() {
        return "Appartments{" +
                "apptId=" + apptId +
                ", apptFloor=" + apptFloor +
                ", apptArea=" + apptArea +
                ", apptNumber='" + apptNumber + '\'' +
                ", owner=" + owner +
                ", block=" + block +
                '}';
    }
}
