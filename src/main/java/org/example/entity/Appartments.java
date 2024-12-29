package org.example.entity;

import javax.persistence.*;

import org.example.utils.*;;

@Entity
@Table(name = "Appartments", schema = "mydb")
public class Appartments {

    @Id
    @Column(name = "ApptID", nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int apptId;

    @Column(name = "ApptFloor",nullable = false)
    private Integer apptFloor;

    @Column(name = "ApptArea",nullable = false)
    private Double apptArea;

    @Column(name = "ApptNumber",nullable = false, length = 10)
    private String apptNumber;

    @ManyToOne
    @JoinColumn(name = "OwnersOwnerID", nullable = false, referencedColumnName = "OwnerID",
                foreignKey = @ForeignKey(name = "fk_Appartments_Owners1"))
    private Owners owner;

    @ManyToOne
    @JoinColumn(name = "ApptBlockID", nullable = false, referencedColumnName = "BlockID",
                foreignKey = @ForeignKey(name = "fk_Appartments_Blocks1"))
    private Blocks block;

    public Appartments() {
        this.apptFloor = 0;
        this.apptArea = 0.0;
        this.apptNumber = "";
        this.owner = null;
        this.block = null;
    }

    //random
    public Appartments(Owners owner , Blocks block)
    {
        this.apptFloor = VladoRandoma.randomInt(15);
        this.apptArea =  VladoRandoma.randomSmallDouble();
        this.apptNumber = VladoRandoma.generateStringNumbber(2);
        this.owner = owner;
        this.block = block;
    }

    public Appartments(Integer apptFloor , Double apptArea , String apptNumber, Owners owner , Blocks block)
    {
        this.apptFloor = apptFloor;
        this.apptArea = apptArea;
        this.apptNumber = apptNumber;
        this.owner = owner;
        this.block = block;
    }

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
