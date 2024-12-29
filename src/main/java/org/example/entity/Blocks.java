package org.example.entity;

import javax.persistence.*;

import org.example.utils.VladoRandoma;

@Entity
@Table(name = "Blocks", schema = "mydb", uniqueConstraints = {
    @UniqueConstraint(columnNames = "BlockID")
})
public class Blocks {

    @Id
    @Column(name = "BlockID", nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int blockId;

    @Column(name = "BlockAdress",nullable = false, length = 45)
    private String blockAddress;

    @Column(name = "BlockFloors",nullable = false)
    private Integer blockFloors;

    @Column(name = "BlockName",nullable = false , length = 45)
    private String blockName;

    @Column(name = "BlockPart",nullable = false)
    private Double blockPart;

    @ManyToOne
    @JoinColumn(name = "TaxTaxId", nullable = false, referencedColumnName = "TaxId",
                foreignKey = @ForeignKey(name = "fk_Blocks_Tax1"))
    private Tax tax;

    @ManyToOne
    @JoinColumn(name = "EmployeeEmpID", referencedColumnName = "EmpID", nullable = false, foreignKey = @ForeignKey(name = "fk_Blocks_Employee1_idx"))
    private Employee employee;

    public Blocks() {
        this.blockAddress= "";
        this.blockFloors= 0;
        this.blockName= "";
        this.blockPart= 0.0;
        this.tax = null;
        this.employee= null;
    }

    public Blocks(Tax tax, Employee employee) {
        this.blockAddress = VladoRandoma.generateAdress();
        this.blockFloors = VladoRandoma.randomInt(15);
        this.blockName = VladoRandoma.generateName();
        this.blockPart = VladoRandoma.randomSmallDouble();
        this.tax = tax;
        this.employee= employee;
    }

    public Blocks(String blockAddress, Integer blockFloors, String blockName, Double blockPart, Tax tax, Employee employee) {
        this.blockAddress= blockAddress;
        this.blockFloors= blockFloors;
        this.blockName= blockName;
        this.blockPart= blockPart;
        this.tax = tax;
        this.employee= employee;
    }

    // Getters and Setters
    public int getBlockId() {
        return blockId;
    }

    public void setBlockId(int blockId) {
        this.blockId = blockId;
    }

    public String getBlockAddress() {
        return blockAddress;
    }

    public void setBlockAddress(String blockAddress) {
        this.blockAddress = blockAddress;
    }

    public Integer getBlockFloors() {
        return blockFloors;
    }

    public void setBlockFloors(Integer blockFloors) {
        this.blockFloors = blockFloors;
    }

    public String getBlockName() {
        return blockName;
    }

    public void setBlockName(String blockName) {
        this.blockName = blockName;
    }

    public Double getBlockPart() {
        return blockPart;
    }

    public void setBlockPart(Double blockPart) {
        this.blockPart = blockPart;
    }

    public Tax getTax() {
        return tax;
    }

    public void setTax(Tax tax) {
        this.tax = tax;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    // Optional: toString() method for debugging
    @Override
    public String toString() {
        return "Blocks{" +
                "blockId=" + blockId +
                ", blockAddress='" + blockAddress + '\'' +
                ", blockFloors=" + blockFloors +
                ", blockName='" + blockName + '\'' +
                ", blockPart='" + blockPart + '\'' +
                ", tax=" + tax +
                ", employee=" + employee +
                '}';
    }
}
