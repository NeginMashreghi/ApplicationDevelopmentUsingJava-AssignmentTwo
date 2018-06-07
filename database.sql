CREATE DATABASE COMP3095;
USE COMP3095;
GRANT ALL ON COMP3095.* to 'admin'@'localhost' identified by 'admin';

CREATE TABLE `departments` (
  `id` INT(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(255) NOT NULL,
  `location` VARCHAR(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*
References: departments(`id`)
*/
CREATE TABLE `employees` (
  `id` INT(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `first_name` VARCHAR(255) NOT NULL,
  `last_name` VARCHAR(255) NOT NULL,
  `email` VARCHAR(255) NOT NULL,
  `role` VARCHAR(10) NOT NULL,
  `employee_id` INT(10) UNSIGNED NOT NULL,
  `hire_date` DATE NOT NULL,
  `department_id` INT(10) UNSIGNED NOT NULL,
  `username` VARCHAR(32),
  `password` VARCHAR(64),
  `cookie_secret` VARCHAR(64),
  `cookie_expiration` DATE,
  PRIMARY KEY (`id`),
  KEY `FK_employees_departments` (`department_id`),
  CONSTRAINT `FK_employees_departments` FOREIGN KEY (`department_id`) REFERENCES `departments` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*
References: departments(`id`)
*/
CREATE TABLE `groups` (
  `id` INT(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `department_id` INT(10) UNSIGNED NOT NULL,
  `name` VARCHAR(255) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_groups_departments` (`department_id`),
  CONSTRAINT `FK_groups_departments` FOREIGN KEY (`department_id`) REFERENCES `departments` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*
References: employees(`id`), groups(`id`)
*/
CREATE TABLE `group_members` (
  `employee_id` INT(10) UNSIGNED NOT NULL,
  `group_id` INT(10) UNSIGNED NOT NULL,
  KEY `FK_group_members_employees` (`employee_id`),
  KEY `FK_group_members_groups` (`group_id`),
  CONSTRAINT `FK_group_members_employees` FOREIGN KEY (`employee_id`) REFERENCES `employees` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `FK_group_members_groups` FOREIGN KEY (`group_id`) REFERENCES `groups` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*
References: departments(`id`)
*/
CREATE TABLE `report_templates` (
  `id` INT(10) UNSIGNED AUTO_INCREMENT NOT NULL,
  `name` VARCHAR(32) NOT NULL,
  `department_id` INT(10) UNSIGNED NOT NULL,
  `date_created` DATE NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_report_templates_department` (`department_id`),
  CONSTRAINT `FK_report_templates_departments` FOREIGN KEY (`department_id`) REFERENCES `departments` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*
References: report_templates(`id`)
*/
CREATE TABLE `report_section_templates` (
  `id` INT(10) UNSIGNED AUTO_INCREMENT NOT NULL,
  `name` VARCHAR(32) NOT NULL,
  `report_template_id` INT(10) UNSIGNED NOT NULL,
  `is_short` BOOLEAN NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_report_section_templates_report_template` (`report_template_id`),
  CONSTRAINT `FK_report_section_templates_report_template` FOREIGN KEY (`report_template_id`) REFERENCES `report_templates` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*
References: report_section_templates(`id`)
*/
CREATE TABLE `section_criteria_templates` (
  `id` INT (10) UNSIGNED AUTO_INCREMENT NOT NULL,
  `name` VARCHAR(32) NOT NULL,
  `max_evaluation` INT(1),
  `report_section_id` INT (10) UNSIGNED NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_section_criteria_template_report_section_templates` (`report_section_id`),
  CONSTRAINT `FK_section_criteria_template_report_section_templates` FOREIGN KEY (`report_section_id`) REFERENCES `report_section_templates` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*
References: report_templates(`id`)
*/
CREATE TABLE `reports` (
  `id` INT(10) UNSIGNED AUTO_INCREMENT NOT NULL,
  `title` VARCHAR(32) NOT NULL,
  `template_id` INT(10) UNSIGNED NOT NULL,
  `report_type` INT(10) UNSIGNED NOT NULL, /* 0 = group, 1 = employee */
  `creation_date` DATE NOT NULL,
  PRIMARY KEY(`id`),
  KEY `FK_reports_report_template` (`template_id`),
  CONSTRAINT `FK_reports_report_template` FOREIGN KEY (`template_id`) REFERENCES `report_templates` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*
References: group(`id`), reports(`id`)
*/
CREATE TABLE `group_reports` (
  `id` INT(10) UNSIGNED AUTO_INCREMENT NOT NULL,
  `group_id` INT(10) UNSIGNED NOT NULL,
  `report_id` INT(10) UNSIGNED NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_group_reports_group` (`group_id`),
  CONSTRAINT `FK_group_reports_group` FOREIGN KEY (`group_id`) REFERENCES `groups` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  KEY `FK_group_reports_reports` (`report_id`),
  CONSTRAINT `FK_group_reports_reports` FOREIGN KEY (`report_id`) REFERENCES `reports` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*
References: employees(`id`), reports(`id`)
*/
CREATE TABLE `employee_reports` (
  `id` INT(10) UNSIGNED AUTO_INCREMENT NOT NULL,
  `employee_id` INT(10) UNSIGNED NOT NULL,
  `report_id` INT(10) UNSIGNED NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_employee_reports_employees` (`employee_id`),
  CONSTRAINT `FK_employee_reports_employees` FOREIGN KEY (`employee_id`) REFERENCES `employees` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  KEY `FK_employee_reports_reports` (`report_id`),
  CONSTRAINT `FK_employee_reports_reports` FOREIGN KEY (`report_id`) REFERENCES `reports` (`id`) ON DELETE CASCADE ON UPDATE CASCADE

) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*
References: report_section_templates(`id`)
*/
CREATE TABLE `report_sections` (
  `id` INT(10) UNSIGNED AUTO_INCREMENT NOT NULL,
  `evaluation` VARCHAR (512) NOT NULL,
  `section_id` INT (10) UNSIGNED NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_report_section_report_section` (`section_id`),
  CONSTRAINT `FK_report_section_report_section` FOREIGN KEY (`section_id`) REFERENCES `report_section_templates` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*
References: section_criteria_templates(`id`)
*/
CREATE TABLE `section_criteria` (
  `id` INT(10) UNSIGNED AUTO_INCREMENT NOT NULL,
  `evaluation` INT(1) UNSIGNED NOT NULL,
  `criteria_id` INT(10) UNSIGNED NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_section_criteria_section_criteria` (`criteria_id`),
  CONSTRAINT `FK_section_criteria_section_criteria` FOREIGN KEY (`criteria_id`) REFERENCES `section_criteria_templates` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*
References: employees(`id`)
*/
CREATE TABLE attendance (
    `id` INT(10) UNSIGNED AUTO_INCREMENT NOT NULL,
    `employee_id` INT(10) UNSIGNED NOT NULL,
    `date` DATE NOT NULL,
    `present` BOOLEAN NOT NULL,
    PRIMARY KEY (`id`),
    KEY `FK_attendance_employee` (`employee_id`),
    CONSTRAINT `FK_attendance_employee` FOREIGN KEY (`employee_id`) REFERENCES `employees` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO `departments` (`id`, `name`, `location`) VALUES (1, "Administration", "7th Circle of Hell");
INSERT INTO `employees` (`id`, `first_name`, `last_name`, `email`, `role`, `employee_id`, `hire_date`, `department_id`, `username`, `password`) VALUES
	(1, 'Wesley', 'Greenwood', '100990357@georgebrown.ca', 'admin', 0, CURRENT_DATE, 1, 'admin', 'admin');