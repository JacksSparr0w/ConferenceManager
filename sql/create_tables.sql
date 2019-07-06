USE `meetups`;

CREATE TABLE `user`
(
  `id`         INTEGER PRIMARY KEY NOT NULL AUTO_INCREMENT,
  `login`      VARCHAR(255) UNIQUE NOT NULL,
  `password`   VARCHAR(32)         NOT NULL,
  `email`      VARCHAR(255),
  `permission` ENUM ('administrator', 'user')
) ENGINE = INNODB
  DEFAULT CHARACTER SET utf8;

CREATE TABLE `user_info`
(
  `id`                   INTEGER PRIMARY KEY NOT NULL AUTO_INCREMENT,
  `name`                 VARCHAR(255)        NOT NULL,
  `surname`              VARCHAR(255)        NOT NULL,
  `about`                TEXT,
  `picture_link`         VARCHAR(255)                         DEFAULT NULL,
  `date_of_birth`        DATE                NOT NULL,
  `date_of_registration` DATETIME            NOT NULL,
  `gender`               ENUM ('male', 'female', 'no matter') DEFAULT 'no matter'
) ENGINE = INNODB
  DEFAULT CHARACTER SET utf8;

CREATE TABLE `country`
(
  `code` CHAR(2) UNIQUE PRIMARY KEY NOT NULL,
  `name` VARCHAR(255)               NOT NULL
) ENGINE = INNODB
  DEFAULT CHARACTER SET utf8;

CREATE TABLE `event_info`
(
  `id`           INTEGER PRIMARY KEY                      NOT NULL AUTO_INCREMENT,
  `name`         VARCHAR(255)                             NOT NULL,
  `description`  TEXT,
  `date`         DATETIME                                 NOT NULL,
  `type`         ENUM ('conf', 'meetup', 'anything_else') NOT NULL,
  `country_code` CHAR(2)
) ENGINE = INNODB
  DEFAULT CHARACTER SET utf8;

CREATE TABLE `event_status`
(
  `id`         INTEGER PRIMARY KEY NOT NULL AUTO_INCREMENT,
  `status`     ENUM ('created', 'running', 'done', 'failure'),
  `free_space` INTEGER,
  `capacity`   INTEGER
) ENGINE = INNODB
  DEFAULT CHARACTER SET utf8;

CREATE TABLE `filling`
(
  `event_id`  INTEGER,
  `user_id`   INTEGER,
  `user_role` ENUM ('listener', 'teller')
) ENGINE = INNODB
  DEFAULT CHARACTER SET utf8;

ALTER TABLE `user_info`
  ADD FOREIGN KEY (`id`) REFERENCES `user` (`id`)
    ON UPDATE CASCADE
    ON DELETE RESTRICT;

ALTER TABLE `event_info`
  ADD FOREIGN KEY (`country_code`) REFERENCES `country` (`code`)
    ON UPDATE CASCADE
    ON DELETE RESTRICT;

ALTER TABLE `event_status`
  ADD FOREIGN KEY (`id`) REFERENCES `event_info` (`id`)
    ON UPDATE CASCADE
    ON DELETE RESTRICT;


ALTER TABLE `filling`
  ADD FOREIGN KEY (`event_id`) REFERENCES `event_info` (`id`)
    ON UPDATE CASCADE
    ON DELETE CASCADE;

ALTER TABLE `filling`
  ADD FOREIGN KEY (`user_id`) REFERENCES `user` (`id`)
    ON UPDATE CASCADE
    ON DELETE CASCADE;