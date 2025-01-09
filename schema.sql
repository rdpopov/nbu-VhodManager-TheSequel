-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `mydb` DEFAULT CHARACTER SET utf8mb3 ;
USE `mydb` ;

-- -----------------------------------------------------
-- Table `mydb`.`Company`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`Company` (
  `CompanyID` INT NOT NULL AUTO_INCREMENT,
  `CompanyName` VARCHAR(45) NOT NULL,
  `CompanyAdress` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`CompanyID`),
  UNIQUE INDEX `CompanyID_UNIQUE` (`CompanyID` ASC) VISIBLE)
ENGINE = InnoDB
AUTO_INCREMENT = 28742
DEFAULT CHARACTER SET = utf8mb3;


-- -----------------------------------------------------
-- Table `mydb`.`Employee`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`Employee` (
  `EmpID` INT NOT NULL AUTO_INCREMENT,
  `EmpFirstname` VARCHAR(45) NOT NULL,
  `EmpLastname` VARCHAR(45) NOT NULL,
  `EmpEGN` VARCHAR(15) NOT NULL,
  `EmpTel` VARCHAR(15) NOT NULL,
  `CompanyCompanyID` INT NOT NULL,
  PRIMARY KEY (`EmpID`),
  UNIQUE INDEX `EmployeeID_UNIQUE` (`EmpID` ASC) VISIBLE,
  INDEX `fk_Employee_Company_idx` (`CompanyCompanyID` ASC) VISIBLE,
  CONSTRAINT `fk_Employee_Company`
    FOREIGN KEY (`CompanyCompanyID`)
    REFERENCES `mydb`.`Company` (`CompanyID`))
ENGINE = InnoDB
AUTO_INCREMENT = 32274
DEFAULT CHARACTER SET = utf8mb3;


-- -----------------------------------------------------
-- Table `mydb`.`Tax`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`Tax` (
  `TaxId` INT NOT NULL AUTO_INCREMENT,
  `TaxPerAdult` DOUBLE NOT NULL,
  `PerChild` DOUBLE NOT NULL,
  `TaxArea` DOUBLE NOT NULL,
  `TaxPet` DOUBLE NOT NULL,
  `TaxFlat` DOUBLE NOT NULL,
  `TaxRepair` DOUBLE NOT NULL,
  PRIMARY KEY (`TaxId`),
  UNIQUE INDEX `TaxId_UNIQUE` (`TaxId` ASC) VISIBLE)
ENGINE = InnoDB
AUTO_INCREMENT = 32620
DEFAULT CHARACTER SET = utf8mb3;


-- -----------------------------------------------------
-- Table `mydb`.`Blocks`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`Blocks` (
  `BlockID` INT NOT NULL AUTO_INCREMENT,
  `BlockAdress` VARCHAR(45) NOT NULL,
  `BlockFloors` INT NOT NULL,
  `BlockName` VARCHAR(45) NOT NULL,
  `BlockPart` DOUBLE NOT NULL,
  `TaxTaxId` INT NOT NULL,
  `EmployeeEmpID` INT NOT NULL,
  PRIMARY KEY (`BlockID`),
  UNIQUE INDEX `BlockID_UNIQUE` (`BlockID` ASC) VISIBLE,
  INDEX `fk_Blocks_Tax1_idx` (`TaxTaxId` ASC) VISIBLE,
  INDEX `fk_Blocks_Employee1_idx` (`EmployeeEmpID` ASC) VISIBLE,
  CONSTRAINT `fk_Blocks_Employee1`
    FOREIGN KEY (`EmployeeEmpID`)
    REFERENCES `mydb`.`Employee` (`EmpID`),
  CONSTRAINT `fk_Blocks_Tax1`
    FOREIGN KEY (`TaxTaxId`)
    REFERENCES `mydb`.`Tax` (`TaxId`))
ENGINE = InnoDB
AUTO_INCREMENT = 32621
DEFAULT CHARACTER SET = utf8mb3;


-- -----------------------------------------------------
-- Table `mydb`.`Owners`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`Owners` (
  `OwnerID` INT NOT NULL AUTO_INCREMENT,
  `OwnerFirstname` VARCHAR(45) NOT NULL,
  `OwnerLastname` VARCHAR(45) NOT NULL,
  `OwnerPhone` VARCHAR(15) NOT NULL,
  `OwnerEGN` VARCHAR(15) NOT NULL,
  PRIMARY KEY (`OwnerID`),
  UNIQUE INDEX `OwnerID_UNIQUE` (`OwnerID` ASC) VISIBLE)
ENGINE = InnoDB
AUTO_INCREMENT = 32671
DEFAULT CHARACTER SET = utf8mb3;


-- -----------------------------------------------------
-- Table `mydb`.`Appartments`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`Appartments` (
  `ApptID` INT NOT NULL AUTO_INCREMENT,
  `ApptFloor` INT NOT NULL,
  `ApptArea` DOUBLE NOT NULL,
  `ApptNumber` VARCHAR(10) NOT NULL,
  `OwnersOwnerID` INT NOT NULL,
  `ApptBlockID` INT NOT NULL,
  PRIMARY KEY (`ApptID`),
  UNIQUE INDEX `ApptID_UNIQUE` (`ApptID` ASC) VISIBLE,
  INDEX `fk_Appartments_Blocks1_idx` (`ApptBlockID` ASC) VISIBLE,
  INDEX `fk_Appartments_Owners1_idx` (`OwnersOwnerID` ASC) VISIBLE,
  CONSTRAINT `fk_Appartments_Blocks1`
    FOREIGN KEY (`ApptBlockID`)
    REFERENCES `mydb`.`Blocks` (`BlockID`),
  CONSTRAINT `fk_Appartments_Owners1`
    FOREIGN KEY (`OwnersOwnerID`)
    REFERENCES `mydb`.`Owners` (`OwnerID`))
ENGINE = InnoDB
AUTO_INCREMENT = 32672
DEFAULT CHARACTER SET = utf8mb3;


-- -----------------------------------------------------
-- Table `mydb`.`Inhabitants`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`Inhabitants` (
  `InhID` INT NOT NULL AUTO_INCREMENT,
  `InhFirstname` VARCHAR(45) NOT NULL,
  `InhLastname` VARCHAR(45) NOT NULL,
  `InhDateOfBirth` DATE NOT NULL,
  `AppartmentsApptID` INT NOT NULL,
  PRIMARY KEY (`InhID`),
  UNIQUE INDEX `InhID_UNIQUE` (`InhID` ASC) VISIBLE,
  INDEX `fk_Inhabitants_Appartments1_idx` (`AppartmentsApptID` ASC) VISIBLE,
  CONSTRAINT `fk_Inhabitants_Appartments1`
    FOREIGN KEY (`AppartmentsApptID`)
    REFERENCES `mydb`.`Appartments` (`ApptID`))
ENGINE = InnoDB
AUTO_INCREMENT = 32674
DEFAULT CHARACTER SET = utf8mb3;


-- -----------------------------------------------------
-- Table `mydb`.`Paid`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`Paid` (
  `PaidID` INT NOT NULL AUTO_INCREMENT,
  `TaxTaxId` INT NOT NULL,
  `AppartmentsApptID` INT NOT NULL,
  `PayAmount` DOUBLE NOT NULL,
  `PaidOn` DATE NULL DEFAULT NULL,
  `PayTime` DATE NOT NULL,
  PRIMARY KEY (`PaidID`),
  UNIQUE INDEX `PaidID_UNIQUE` (`PaidID` ASC) VISIBLE,
  INDEX `fk_Paid_Tax1_idx` (`TaxTaxId` ASC) VISIBLE,
  INDEX `fk_Paid_Appartments1_idx` (`AppartmentsApptID` ASC) VISIBLE,
  CONSTRAINT `fk_Paid_Appartments1`
    FOREIGN KEY (`AppartmentsApptID`)
    REFERENCES `mydb`.`Appartments` (`ApptID`),
  CONSTRAINT `fk_Paid_Tax1`
    FOREIGN KEY (`TaxTaxId`)
    REFERENCES `mydb`.`Tax` (`TaxId`))
ENGINE = InnoDB
AUTO_INCREMENT = 621
DEFAULT CHARACTER SET = utf8mb3;


-- -----------------------------------------------------
-- Table `mydb`.`Pets`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`Pets` (
  `idPets` INT NOT NULL AUTO_INCREMENT,
  `Appartments_ApptID` INT NOT NULL,
  PRIMARY KEY (`idPets`),
  UNIQUE INDEX `idPets_UNIQUE` (`idPets` ASC) VISIBLE,
  INDEX `fk_Pets_Appartments1_idx` (`Appartments_ApptID` ASC) VISIBLE,
  CONSTRAINT `fk_Pets_Appartments1`
    FOREIGN KEY (`Appartments_ApptID`)
    REFERENCES `mydb`.`Appartments` (`ApptID`))
ENGINE = InnoDB
AUTO_INCREMENT = 618
DEFAULT CHARACTER SET = utf8mb3;


-- -----------------------------------------------------
-- Table `mydb`.`hibernate_sequence`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`hibernate_sequence` (
  `next_val` BIGINT NULL DEFAULT NULL)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb3;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
