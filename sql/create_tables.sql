USE `meetups`;

CREATE TABLE `user`
(
  `id`         INTEGER PRIMARY KEY NOT NULL AUTO_INCREMENT,
  `login`      VARCHAR(255) UNIQUE NOT NULL,
  `password`   VARCHAR(32)         NOT NULL,
  `permission` ENUM ('administrator', 'user')
) ENGINE = INNODB
  DEFAULT CHARACTER SET utf8;

CREATE TABLE `user_info`
(
  `id`                   INTEGER PRIMARY KEY NOT NULL AUTO_INCREMENT,
  `user_id`              INTEGER             NOT NULL,
  `name`                 VARCHAR(255)        NOT NULL,
  `surname`              VARCHAR(255)        NOT NULL,
  `about`                TEXT,
  `picture_link`         VARCHAR(255)                         DEFAULT NULL,
  `email`                VARCHAR(255),
  `date_of_birth`        DATE                NOT NULL,
  `date_of_registration` DATETIME            NOT NULL,
  `gender`               ENUM ('male', 'female', 'no matter') DEFAULT 'no matter'
) ENGINE = INNODB
  DEFAULT CHARACTER SET utf8;


CREATE TABLE `event_info`
(
  `id`           INTEGER PRIMARY KEY NOT NULL AUTO_INCREMENT,
  `name`         VARCHAR(255)        NOT NULL,
  `description`  TEXT,
  `date`         DATETIME            NOT NULL,
  `country_code` CHAR(2),
  `status`       ENUM ('created', 'running', 'done', 'failure'),
  `capacity`     INTEGER
) ENGINE = INNODB
  DEFAULT CHARACTER SET utf8;

CREATE TABLE `filling`
(
  `event_id`  INTEGER PRIMARY KEY,
  `user_id`   INTEGER,
  `user_role` ENUM ('listener', 'teller')
) ENGINE = INNODB
  DEFAULT CHARACTER SET utf8;

ALTER TABLE `user_info`
  ADD FOREIGN KEY (`user_id`) REFERENCES `user` (`id`)
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