# Store procedure demo

Database: MySQL
User name: root


DELIMITER $$
--
-- Procedures
--
CREATE DEFINER=`root`@`localhost` PROCEDURE `ADD_EMPLOYEE_PROCEDURE` (IN `name` VARCHAR(100))  BEGIN

insert into employee(id,name) values (DEFAULT,name);


END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `ALL_EMPLOYEE` (OUT `id` INT, OUT `name` VARCHAR(255))  SELECT * FROM employee$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `UPDATE_EMPLOYEE_PROCEDURE` (IN `id` INT, IN `name` VARCHAR(255))  BEGIN

UPDATE employee

SET

name = name WHERE id = id;


END$$

DELIMITER ;


CREATE TABLE `employee` (
`id` bigint(20) NOT NULL,
`name` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;


ALTER TABLE `employee`
ADD PRIMARY KEY (`id`);


ALTER TABLE `employee`
MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT;
COMMIT;