-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

-- -----------------------------------------------------
-- Schema library
-- -----------------------------------------------------
DROP SCHEMA IF EXISTS `library` ;

-- -----------------------------------------------------
-- Schema library
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `library` DEFAULT CHARACTER SET utf8 ;
USE `library` ;

-- -----------------------------------------------------
-- Table `library`.`login_data`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `library`.`login_data` ;

CREATE TABLE IF NOT EXISTS `library`.`login_data` (
  `login_data_id` INT NOT NULL AUTO_INCREMENT,
  `email` VARCHAR(255) NOT NULL,
  `password` VARCHAR(255) NOT NULL,
  `role_name` ENUM('READER', 'LIBRARIAN') NOT NULL,
  PRIMARY KEY (`login_data_id`))
ENGINE = InnoDB;

CREATE UNIQUE INDEX `login_UNIQUE` ON `library`.`login_data` (`email` ASC);


-- -----------------------------------------------------
-- Table `library`.`personal_data`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `library`.`personal_data` ;

CREATE TABLE IF NOT EXISTS `library`.`personal_data` (
  `personal_data_id` INT NOT NULL AUTO_INCREMENT,
  `first_name` VARCHAR(45) NOT NULL,
  `last_name` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`personal_data_id`))
ENGINE = InnoDB;

CREATE INDEX `last_name` ON `library`.`personal_data` (`last_name` ASC);


-- -----------------------------------------------------
-- Table `library`.`users`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `library`.`users` ;

CREATE TABLE IF NOT EXISTS `library`.`users` (
  `user_id` INT NOT NULL AUTO_INCREMENT,
  `login_data_id` INT NOT NULL,
  `personal_data_id` INT NOT NULL,
  PRIMARY KEY (`user_id`),
  CONSTRAINT `fk_users_login_data1`
    FOREIGN KEY (`login_data_id`)
    REFERENCES `library`.`login_data` (`login_data_id`)
    ON DELETE NO ACTION
    ON UPDATE CASCADE,
  CONSTRAINT `fk_users_personal_data1`
    FOREIGN KEY (`personal_data_id`)
    REFERENCES `library`.`personal_data` (`personal_data_id`)
    ON DELETE NO ACTION
    ON UPDATE CASCADE)
ENGINE = InnoDB;

CREATE INDEX `fk_login_data` ON `library`.`users` (`login_data_id` ASC);

CREATE INDEX `fk_personal_data` ON `library`.`users` (`personal_data_id` ASC);


-- -----------------------------------------------------
-- Table `library`.`book_numbers`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `library`.`book_numbers` ;

CREATE TABLE IF NOT EXISTS `library`.`book_numbers` (
  `book_number_id` INT NOT NULL AUTO_INCREMENT,
  `book_number` INT NOT NULL,
  PRIMARY KEY (`book_number_id`))
ENGINE = InnoDB;

CREATE UNIQUE INDEX `book_number_UNIQUE` ON `library`.`book_numbers` (`book_number` ASC);


-- -----------------------------------------------------
-- Table `library`.`books`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `library`.`books` ;

CREATE TABLE IF NOT EXISTS `library`.`books` (
  `book_id` INT NOT NULL AUTO_INCREMENT,
  `title` VARCHAR(255) NOT NULL,
  `author` VARCHAR(255) NOT NULL,
  `book_number_id` INT NOT NULL,
  `book_status` ENUM('AVAILABLE', 'UNAVAILABLE') NOT NULL DEFAULT 'AVAILABLE',
  PRIMARY KEY (`book_id`),
  CONSTRAINT `fk_books_book_numbers1`
    FOREIGN KEY (`book_number_id`)
    REFERENCES `library`.`book_numbers` (`book_number_id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB;

CREATE INDEX `title` ON `library`.`books` (`title` ASC);

CREATE INDEX `author` ON `library`.`books` (`author` ASC);

CREATE INDEX `fk_book_number_id` ON `library`.`books` (`book_number_id` ASC);


-- -----------------------------------------------------
-- Table `library`.`orders`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `library`.`orders` ;

CREATE TABLE IF NOT EXISTS `library`.`orders` (
  `order_id` INT NOT NULL AUTO_INCREMENT,
  `user_id` INT NOT NULL,
  `book_id` INT NOT NULL,
  `date_receive` TIMESTAMP(0) NULL,
  `date_return` TIMESTAMP(0) NULL,
  `reading_place` ENUM('READING_ROOM', 'SUBSCRIPTION') NOT NULL,
  `order_status` ENUM('OPEN', 'CLOSED') NOT NULL,
  PRIMARY KEY (`order_id`),
  CONSTRAINT `fk_orders_users1`
    FOREIGN KEY (`user_id`)
    REFERENCES `library`.`users` (`user_id`)
    ON DELETE NO ACTION
    ON UPDATE CASCADE,
  CONSTRAINT `fk_orders_books1`
    FOREIGN KEY (`book_id`)
    REFERENCES `library`.`books` (`book_id`)
    ON DELETE NO ACTION
    ON UPDATE CASCADE)
ENGINE = InnoDB;

CREATE INDEX `fk_user_id` ON `library`.`orders` (`user_id` ASC);

CREATE INDEX `fk_book_id` ON `library`.`orders` (`book_id` ASC);

CREATE INDEX `order_status` ON `library`.`orders` (`order_status` ASC);


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;

-- -----------------------------------------------------
-- Data for table `library`.`login_data`
-- -----------------------------------------------------
START TRANSACTION;
USE `library`;
INSERT INTO `library`.`login_data` (`login_data_id`, `email`, `password`, `role_name`) VALUES (DEFAULT, 'ivan@email.ua', '123', 'READER');
INSERT INTO `library`.`login_data` (`login_data_id`, `email`, `password`, `role_name`) VALUES (DEFAULT, 'petr@email.ua', '234', 'READER');
INSERT INTO `library`.`login_data` (`login_data_id`, `email`, `password`, `role_name`) VALUES (DEFAULT, 'sidor@email.ua', '345', 'READER');
INSERT INTO `library`.`login_data` (`login_data_id`, `email`, `password`, `role_name`) VALUES (DEFAULT, 'maria@email.ua', '456', 'LIBRARIAN');

COMMIT;


-- -----------------------------------------------------
-- Data for table `library`.`personal_data`
-- -----------------------------------------------------
START TRANSACTION;
USE `library`;
INSERT INTO `library`.`personal_data` (`personal_data_id`, `first_name`, `last_name`) VALUES (DEFAULT, 'Иван', 'Иванов');
INSERT INTO `library`.`personal_data` (`personal_data_id`, `first_name`, `last_name`) VALUES (DEFAULT, 'Петр', 'Петров');
INSERT INTO `library`.`personal_data` (`personal_data_id`, `first_name`, `last_name`) VALUES (DEFAULT, 'Сидор', 'Сидоров');
INSERT INTO `library`.`personal_data` (`personal_data_id`, `first_name`, `last_name`) VALUES (DEFAULT, 'Мария', 'Строгая');

COMMIT;


-- -----------------------------------------------------
-- Data for table `library`.`users`
-- -----------------------------------------------------
START TRANSACTION;
USE `library`;
INSERT INTO `library`.`users` (`user_id`, `login_data_id`, `personal_data_id`) VALUES (DEFAULT, 1, 1);
INSERT INTO `library`.`users` (`user_id`, `login_data_id`, `personal_data_id`) VALUES (DEFAULT, 2, 2);
INSERT INTO `library`.`users` (`user_id`, `login_data_id`, `personal_data_id`) VALUES (DEFAULT, 3, 3);
INSERT INTO `library`.`users` (`user_id`, `login_data_id`, `personal_data_id`) VALUES (DEFAULT, 4, 4);

COMMIT;


-- -----------------------------------------------------
-- Data for table `library`.`book_numbers`
-- -----------------------------------------------------
START TRANSACTION;
USE `library`;
INSERT INTO `library`.`book_numbers` (`book_number_id`, `book_number`) VALUES (DEFAULT, 1040001);
INSERT INTO `library`.`book_numbers` (`book_number_id`, `book_number`) VALUES (DEFAULT, 1040002);
INSERT INTO `library`.`book_numbers` (`book_number_id`, `book_number`) VALUES (DEFAULT, 1040003);
INSERT INTO `library`.`book_numbers` (`book_number_id`, `book_number`) VALUES (DEFAULT, 1040004);
INSERT INTO `library`.`book_numbers` (`book_number_id`, `book_number`) VALUES (DEFAULT, 1040005);
INSERT INTO `library`.`book_numbers` (`book_number_id`, `book_number`) VALUES (DEFAULT, 1040006);
INSERT INTO `library`.`book_numbers` (`book_number_id`, `book_number`) VALUES (DEFAULT, 1040007);
INSERT INTO `library`.`book_numbers` (`book_number_id`, `book_number`) VALUES (DEFAULT, 1040008);
INSERT INTO `library`.`book_numbers` (`book_number_id`, `book_number`) VALUES (DEFAULT, 1040009);
INSERT INTO `library`.`book_numbers` (`book_number_id`, `book_number`) VALUES (DEFAULT, 1040010);
INSERT INTO `library`.`book_numbers` (`book_number_id`, `book_number`) VALUES (DEFAULT, 1040011);
INSERT INTO `library`.`book_numbers` (`book_number_id`, `book_number`) VALUES (DEFAULT, 1040012);
INSERT INTO `library`.`book_numbers` (`book_number_id`, `book_number`) VALUES (DEFAULT, 1040013);
INSERT INTO `library`.`book_numbers` (`book_number_id`, `book_number`) VALUES (DEFAULT, 1040014);
INSERT INTO `library`.`book_numbers` (`book_number_id`, `book_number`) VALUES (DEFAULT, 1040015);
INSERT INTO `library`.`book_numbers` (`book_number_id`, `book_number`) VALUES (DEFAULT, 1040016);
INSERT INTO `library`.`book_numbers` (`book_number_id`, `book_number`) VALUES (DEFAULT, 1040017);
INSERT INTO `library`.`book_numbers` (`book_number_id`, `book_number`) VALUES (DEFAULT, 1040018);
INSERT INTO `library`.`book_numbers` (`book_number_id`, `book_number`) VALUES (DEFAULT, 1040019);
INSERT INTO `library`.`book_numbers` (`book_number_id`, `book_number`) VALUES (DEFAULT, 1040020);

COMMIT;


-- -----------------------------------------------------
-- Data for table `library`.`books`
-- -----------------------------------------------------
START TRANSACTION;
USE `library`;
INSERT INTO `library`.`books` (`book_id`, `title`, `author`, `book_number_id`, `book_status`) VALUES (DEFAULT, 'Преступление и наказание', 'Достоевский Ф.М.', 1, 'AVAILABLE');
INSERT INTO `library`.`books` (`book_id`, `title`, `author`, `book_number_id`, `book_status`) VALUES (DEFAULT, 'Преступление и наказание', 'Достоевский Ф.М.', 2, 'AVAILABLE');
INSERT INTO `library`.`books` (`book_id`, `title`, `author`, `book_number_id`, `book_status`) VALUES (DEFAULT, 'Униженные и оскорбленные', 'Достоевский Ф.М.', 3, 'AVAILABLE');
INSERT INTO `library`.`books` (`book_id`, `title`, `author`, `book_number_id`, `book_status`) VALUES (DEFAULT, 'Бесы', 'Достоевский Ф.М.', 4, 'AVAILABLE');
INSERT INTO `library`.`books` (`book_id`, `title`, `author`, `book_number_id`, `book_status`) VALUES (DEFAULT, 'Идиот', 'Достоевский Ф.М.', 5, 'AVAILABLE');
INSERT INTO `library`.`books` (`book_id`, `title`, `author`, `book_number_id`, `book_status`) VALUES (DEFAULT, 'Идиот', 'Достоевский Ф.М.', 6, 'AVAILABLE');
INSERT INTO `library`.`books` (`book_id`, `title`, `author`, `book_number_id`, `book_status`) VALUES (DEFAULT, 'Братья Карамазовы', 'Достоевский Ф.М.', 7, 'AVAILABLE');
INSERT INTO `library`.`books` (`book_id`, `title`, `author`, `book_number_id`, `book_status`) VALUES (DEFAULT, 'Война и мир', 'Толстой Л.Н.', 8, 'AVAILABLE');
INSERT INTO `library`.`books` (`book_id`, `title`, `author`, `book_number_id`, `book_status`) VALUES (DEFAULT, 'Анна Каренина', 'Толстой Л.Н.', 9, 'AVAILABLE');
INSERT INTO `library`.`books` (`book_id`, `title`, `author`, `book_number_id`, `book_status`) VALUES (DEFAULT, 'Петр І', 'Толстой А.Н.', 10, 'UNAVAILABLE');
INSERT INTO `library`.`books` (`book_id`, `title`, `author`, `book_number_id`, `book_status`) VALUES (DEFAULT, 'Кобзар', 'Шевченко Т.Г.', 11, 'AVAILABLE');
INSERT INTO `library`.`books` (`book_id`, `title`, `author`, `book_number_id`, `book_status`) VALUES (DEFAULT, 'Кобзар', 'Шевченко Т.Г.', 12, 'AVAILABLE');
INSERT INTO `library`.`books` (`book_id`, `title`, `author`, `book_number_id`, `book_status`) VALUES (DEFAULT, 'Кобзар', 'Шевченко Т.Г.', 13, 'UNAVAILABLE');
INSERT INTO `library`.`books` (`book_id`, `title`, `author`, `book_number_id`, `book_status`) VALUES (DEFAULT, 'Властелин колец', 'Толкин Д.', 14, 'AVAILABLE');
INSERT INTO `library`.`books` (`book_id`, `title`, `author`, `book_number_id`, `book_status`) VALUES (DEFAULT, 'Хоббит', 'Толкин Д.', 15, 'AVAILABLE');
INSERT INTO `library`.`books` (`book_id`, `title`, `author`, `book_number_id`, `book_status`) VALUES (DEFAULT, 'Сильмариллион', 'Толкин Д.', 16, 'AVAILABLE');
INSERT INTO `library`.`books` (`book_id`, `title`, `author`, `book_number_id`, `book_status`) VALUES (DEFAULT, 'Три товарища', 'Ремарк Э.М.', 17, 'AVAILABLE');
INSERT INTO `library`.`books` (`book_id`, `title`, `author`, `book_number_id`, `book_status`) VALUES (DEFAULT, 'На западном фронте без перемен', 'Ремарк Э.М.', 18, 'AVAILABLE');
INSERT INTO `library`.`books` (`book_id`, `title`, `author`, `book_number_id`, `book_status`) VALUES (DEFAULT, 'Триумфальная арка', 'Ремарк Э.М.', 19, 'AVAILABLE');
INSERT INTO `library`.`books` (`book_id`, `title`, `author`, `book_number_id`, `book_status`) VALUES (DEFAULT, 'Черный обелиск', 'Ремарк Э.М.', 20, 'AVAILABLE');

COMMIT;


-- -----------------------------------------------------
-- Data for table `library`.`orders`
-- -----------------------------------------------------
START TRANSACTION;
USE `library`;
INSERT INTO `library`.`orders` (`order_id`, `user_id`, `book_id`, `date_receive`, `date_return`, `reading_place`, `order_status`) VALUES (DEFAULT, 1, 1, '2017-06-01', '2017-06-01', 'READING_ROOM', 'CLOSED');
INSERT INTO `library`.`orders` (`order_id`, `user_id`, `book_id`, `date_receive`, `date_return`, `reading_place`, `order_status`) VALUES (DEFAULT, 2, 1, '2017-06-02', '2017-06-02', 'READING_ROOM', 'CLOSED');
INSERT INTO `library`.`orders` (`order_id`, `user_id`, `book_id`, `date_receive`, `date_return`, `reading_place`, `order_status`) VALUES (DEFAULT, 1, 5, '2017-06-02', '2017-06-04', 'SUBSCRIPTION', 'CLOSED');
INSERT INTO `library`.`orders` (`order_id`, `user_id`, `book_id`, `date_receive`, `date_return`, `reading_place`, `order_status`) VALUES (DEFAULT, 3, 4, '2017-06-03', '2017-06-11', 'SUBSCRIPTION', 'CLOSED');
INSERT INTO `library`.`orders` (`order_id`, `user_id`, `book_id`, `date_receive`, `date_return`, `reading_place`, `order_status`) VALUES (DEFAULT, 3, 1, '2017-06-03', NULL, 'SUBSCRIPTION', 'CLOSED');
INSERT INTO `library`.`orders` (`order_id`, `user_id`, `book_id`, `date_receive`, `date_return`, `reading_place`, `order_status`) VALUES (DEFAULT, 2, 10, '2017-06-06', '2017-06-08', 'SUBSCRIPTION', 'CLOSED');
INSERT INTO `library`.`orders` (`order_id`, `user_id`, `book_id`, `date_receive`, `date_return`, `reading_place`, `order_status`) VALUES (DEFAULT, 3, 15, '2017-06-06', '2017-06-06', 'READING_ROOM', 'CLOSED');
INSERT INTO `library`.`orders` (`order_id`, `user_id`, `book_id`, `date_receive`, `date_return`, `reading_place`, `order_status`) VALUES (DEFAULT, 1, 18, '2017-06-07', '2017-06-07', 'READING_ROOM', 'CLOSED');
INSERT INTO `library`.`orders` (`order_id`, `user_id`, `book_id`, `date_receive`, `date_return`, `reading_place`, `order_status`) VALUES (DEFAULT, 1, 13, '2017-06-08', '2017-06-08', 'SUBSCRIPTION', 'CLOSED');
INSERT INTO `library`.`orders` (`order_id`, `user_id`, `book_id`, `date_receive`, `date_return`, `reading_place`, `order_status`) VALUES (DEFAULT, 1, 14, '2017-06-09', NULL, 'SUBSCRIPTION', 'CLOSED');
INSERT INTO `library`.`orders` (`order_id`, `user_id`, `book_id`, `date_receive`, `date_return`, `reading_place`, `order_status`) VALUES (DEFAULT, 2, 18, NULL, NULL, 'SUBSCRIPTION', 'OPEN');

COMMIT;
