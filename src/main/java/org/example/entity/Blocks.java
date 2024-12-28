package org.example.entity;

import javax.persistence.*;

@Entity
@Table(name = "Blocks", schema = "mydb", uniqueConstraints = {
    @UniqueConstraint(columnNames = "BlockID")
})
public class Blocks {

    @Id
    @Column(name = "BlockID", nullable = false)
    private int blockId;

    @Column(name = "BlockAdress", length = 45)
    private String blockAddress;

    @Column(name = "BlockFloors")
    private Integer blockFloors;

    @Column(name = "BlockName", length = 45)
    private String blockName;

    @Column(name = "BlockPart", length = 45)
    private String blockPart;

    @ManyToOne
    @JoinColumn(name = "TaxTaxId", nullable = false, referencedColumnName = "TaxId",
                foreignKey = @ForeignKey(name = "fk_Blocks_Tax1"))
    private Tax tax;

    @ManyToOne
    @JoinColumns({
        @JoinColumn(name = "EmployeeEmpID", referencedColumnName = "EmpID", nullable = false),
        @JoinColumn(name = "EmployeeCompanyCompanyID", referencedColumnName = "CompanyCompanyID", nullable = false)
    })
    private Employee employee;

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

    public String getBlockPart() {
        return blockPart;
    }

    public void setBlockPart(String blockPart) {
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
