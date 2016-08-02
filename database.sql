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
  `Url` VARCHAR(300) NOT NULL ,
  `Date` DATETIME NULL ,
  PRIMARY KEY (`ID`) )
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `urldata`.`User_has_Links`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `urldata`.`User_has_Links` (
  `User_ID` INT NOT NULL ,
  `Links_ID` INT NOT NULL ,
  PRIMARY KEY (`User_ID`, `Links_ID`) ,
  INDEX `fk_User_has_Links_Links1_idx` (`Links_ID` ASC) ,
  INDEX `fk_User_has_Links_User_idx` (`User_ID` ASC) ,
  CONSTRAINT `fk_User_has_Links_User`
    FOREIGN KEY (`User_ID` )
    REFERENCES `urldata`.`User` (`ID` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_User_has_Links_Links1`
    FOREIGN KEY (`Links_ID` )
    REFERENCES `urldata`.`Links` (`ID` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

USE `urldata` ;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
