-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
-- -----------------------------------------------------
-- Schema authentication
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema authentication
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `authentication` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci ;
USE `authentication` ;

-- -----------------------------------------------------
-- Table `User`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `User` ;

CREATE TABLE IF NOT EXISTS `User` (
  `ID` INT NOT NULL AUTO_INCREMENT,
  `Username` VARCHAR(45) NULL DEFAULT NULL,
  `Email` VARCHAR(45) NULL DEFAULT NULL,
  `Password` VARCHAR(45) NULL DEFAULT NULL,
  `CreateDate` DATETIME NULL DEFAULT NULL,
  `LastModificationDate` DATETIME NULL DEFAULT NULL,
  `ActiveFlag` TINYINT(1) NULL DEFAULT NULL,
  PRIMARY KEY (`ID`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `RegistrationToken`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `RegistrationToken` ;

CREATE TABLE IF NOT EXISTS `RegistrationToken` (
  `ID` INT NOT NULL AUTO_INCREMENT,
  `Token` VARCHAR(255) NULL DEFAULT NULL,
  `Email` VARCHAR(45) NULL DEFAULT NULL,
  `ExpirationDate` DATETIME NULL DEFAULT NULL,
  `CreateBy` INT NULL DEFAULT NULL,
  PRIMARY KEY (`ID`),
  INDEX `fk_RegistrationToken_User1_idx` (`CreateBy` ASC) VISIBLE,
  CONSTRAINT `fk_RegistrationToken_User1`
    FOREIGN KEY (`CreateBy`)
    REFERENCES `User` (`ID`),
  CONSTRAINT `FKrtc68vvwv64f865gop77sqgwp`
    FOREIGN KEY (`CreateBy`)
    REFERENCES `User` (`ID`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `Role`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `Role` ;

CREATE TABLE IF NOT EXISTS `Role` (
  `ID` INT NOT NULL AUTO_INCREMENT,
  `RoleName` VARCHAR(45) NULL DEFAULT NULL,
  `RoleDescription` VARCHAR(45) NULL DEFAULT NULL,
  `CreateDate` DATETIME NULL DEFAULT NULL,
  `LastModificationDate` DATETIME NULL DEFAULT NULL,
  PRIMARY KEY (`ID`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `UserRole`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `UserRole` ;

CREATE TABLE IF NOT EXISTS `UserRole` (
  `ID` INT NOT NULL AUTO_INCREMENT,
  `UserID` INT NOT NULL,
  `RoleID` INT NOT NULL,
  `ActiveFlag` TINYINT(1) NOT NULL,
  `CreateDate` DATETIME NULL DEFAULT NULL,
  `LastModificationDate` DATETIME NULL DEFAULT NULL,
  PRIMARY KEY (`ID`),
  INDEX `fk_UserRole_User_idx` (`UserID` ASC) VISIBLE,
  INDEX `fk_UserRole_Role1_idx` (`RoleID` ASC) VISIBLE,
  CONSTRAINT `fk_UserRole_Role1`
    FOREIGN KEY (`RoleID`)
    REFERENCES `Role` (`ID`),
  CONSTRAINT `fk_UserRole_User`
    FOREIGN KEY (`UserID`)
    REFERENCES `User` (`ID`),
  CONSTRAINT `FKc0fssbduxa6pyx2ayo9k5pux`
    FOREIGN KEY (`RoleID`)
    REFERENCES `Role` (`ID`),
  CONSTRAINT `FKem32eidog6qrt5ufruuuqnbcw`
    FOREIGN KEY (`UserID`)
    REFERENCES `User` (`ID`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;

insert into Role (RoleName, RoleDescription, CreateDate, LastModificationDate) value ('HR', 'this is a HR', now(), now());
insert into Role (RoleName, RoleDescription, CreateDate, LastModificationDate) value ('Employee', 'this is a Employee', now(), now());

insert into User (Username, Email, Password, CreateDate, LastModificationDate, ActiveFlag) value ('admin', 'admin@email.com', 'admin', now(), now(), true);

insert into UserRole (UserID, RoleID, ActiveFlag, CreateDate, LastModificationDate) value (1, 1, true, now(), now());
insert into UserRole (UserID, RoleID, ActiveFlag, CreateDate, LastModificationDate) value (1, 2, true, now(), now());

SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
