SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

CREATE SCHEMA IF NOT EXISTS `urldata` DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci ;
USE `urldata` ;

-- -----------------------------------------------------
-- Table `urldata`.`User`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `urldata`.`User` (
  `ID` INT NOT NULL ,
  `Name` VARCHAR(45) NOT NULL ,
  PRIMARY KEY (`ID`) )
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `urldata`.`Links`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `urldata`.`Links` (
  `ID` INT NOT NULL ,
  `USER_ID` INT NOT NULL ,
  `Url` VARCHAR(300) NOT NULL ,
  `Date` DATETIME NULL ,
  PRIMARY KEY (`ID`) ,
  FOREIGN KEY (`USER_ID`) REFERENCES `urldata`.'User'('ID') ON DELETE CASCADE )
ENGINE = InnoDB;




USE `urldata` ;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
