CREATE DATABASE `fss` /*!40100 DEFAULT CHARACTER SET utf8 */;


CREATE TABLE IF NOT EXISTS `fss`.`employee` (
  `ID` INT(10) UNSIGNED NOT NULL,
  `NAME` CHAR(30) NOT NULL,
  `EMAIL` CHAR(30) NOT NULL,
  PRIMARY KEY (`id`))
;

CREATE TABLE IF NOT EXISTS `fss`.`message` (
  `ID` INT(10) UNSIGNED NOT NULL,
  `SUBJECT` VARCHAR(255) NOT NULL,
  `BODY` VARCHAR(255) NOT NULL,
  `DATE_CREATED` DATE NULL DEFAULT NULL,
  `SENDER_ID` INT(10) UNSIGNED NOT NULL,
  PRIMARY KEY (`ID`, `SENDER_ID`),
  INDEX `fk_message_employee1_idx` (`SENDER_ID` ASC),
  CONSTRAINT `fk_message_employee1`
    FOREIGN KEY (`SENDER_ID`)
    REFERENCES `fss`.`employee` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);

CREATE TABLE IF NOT EXISTS `fss`.`FILE` (
  `ID` INT NOT NULL,
  `FULL_PATH` VARCHAR(255) NULL,
  `MESSAGE_ID` INT(10) UNSIGNED NOT NULL,
  PRIMARY KEY (`ID`),
  INDEX `fk_FILE_message1_idx` (`MESSAGE_ID` ASC),
  CONSTRAINT `fk_FILE_message1`
    FOREIGN KEY (`MESSAGE_ID`)
    REFERENCES `fss`.`message` (`ID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);


CREATE TABLE IF NOT EXISTS `fss`.`RECIPIENT` (
  `MESSAGE_ID` INT(10) UNSIGNED NOT NULL,
  `EMPLOYEE_ID` INT(10) UNSIGNED NOT NULL,
  `TYPE` VARCHAR(45) NULL,
  PRIMARY KEY (`MESSAGE_ID`, `EMPLOYEE_ID`),
  INDEX `fk_employee_has_message_message1_idx` (`MESSAGE_ID` ASC),
  INDEX `fk_employee_has_message_employee1_idx` (`EMPLOYEE_ID` ASC),
  CONSTRAINT `fk_employee_has_message_message1`
    FOREIGN KEY (`MESSAGE_ID`)
    REFERENCES `fss`.`message` (`ID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_employee_has_message_employee1`
    FOREIGN KEY (`EMPLOYEE_ID`)
    REFERENCES `fss`.`employee` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
;
