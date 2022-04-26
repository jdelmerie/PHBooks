CREATE TABLE `t_books` (
  `id` int PRIMARY KEY AUTO_INCREMENT,
  `title` VARCHAR(100) NOT NULL,
  `author` VARCHAR(100) NOT NULL,
  `publishYear` year,
  `quantity` int DEFAULT 1,
  `price` DOUBLE NOT NULL,
  `state` boolean
)ENGINE = InnoDB;

CREATE TABLE `t_thematics` (
  `id` int PRIMARY KEY AUTO_INCREMENT,
  `name` VARCHAR(100) NOT NULL
)ENGINE = InnoDB;

CREATE TABLE `t_customers` (
  `id` int PRIMARY KEY AUTO_INCREMENT,
  `email` VARCHAR(100) NOT NULL,
  `password` VARCHAR(16) NOT NULL,
  `firstname` VARCHAR(100) NOT NULL,
  `lastname` VARCHAR(100)NOT NULL,
  `address` VARCHAR(255),
  `phone` VARCHAR(10)
)ENGINE = InnoDB;

CREATE TABLE `t_administrator` (
  `id` int PRIMARY KEY AUTO_INCREMENT,
  `email` VARCHAR(100) NOT NULL,
  `password` VARCHAR(16) NOT NULL,
  `name` VARCHAR(100) NOT NULL,
)ENGINE = InnoDB;

CREATE TABLE `t_booksByThematics` (
  `bookId` INT NOT NULL,
  `thematicId` INT NOT NULL
)ENGINE = InnoDB;

CREATE TABLE `t_ordersItems` (
  `id` int PRIMARY KEY AUTO_INCREMENT,
  `bookId` INT NOT NULL,
  `quantity` int DEFAULT 1,
   `price` double NOT NULL,
  `orderId` INT NOT NULL
)ENGINE = InnoDB;

CREATE TABLE `t_orders` (
  `id` int PRIMARY KEY AUTO_INCREMENT,
  `totalAmount` double,
  `date` DATE NOT NULL,
  `customerId` INT NOT NULL
)ENGINE = InnoDB;

ALTER TABLE `t_booksByThematics` ADD FOREIGN KEY (`bookId`) REFERENCES `t_books` (`id`);

ALTER TABLE `t_booksByThematics` ADD FOREIGN KEY (`thematicId`) REFERENCES `t_thematics` (`id`);

ALTER TABLE `t_ordersItems` ADD FOREIGN KEY (`bookId`) REFERENCES `t_books` (`id`);

ALTER TABLE `t_ordersItems` ADD FOREIGN KEY (`orderId`) REFERENCES `t_orders` (`id`);

ALTER TABLE `t_orders` ADD FOREIGN KEY (`customerId`) REFERENCES `t_customers` (`id`);
