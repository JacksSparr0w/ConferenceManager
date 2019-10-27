USE `test`;

CREATE TABLE `user`
(
  `id`         INTEGER PRIMARY KEY NOT NULL AUTO_INCREMENT,
  `login`      VARCHAR(255) UNIQUE NOT NULL,
  `password`   VARCHAR(32)         NOT NULL,
  `permission` INTEGER
) ENGINE = INNODB
  DEFAULT CHARACTER SET utf8;

CREATE TABLE `permission`
(
  id                     int auto_increment primary key,
  name                   varchar(255) unique  not null,
  modify_any_event       tinyint(1) default 0 null,
  change_user_permission tinyint(1) default 0 null,
  all_users              tinyint(1) default 0 null,
  delete_user            tinyint(1) default 0 null,
  add_theme              tinyint(1) default 0 null,
  add_role               tinyint(1) default 0 null,
  add_permission         tinyint(1) default 0 null
) ENGINE = INNODB
  DEFAULT CHARACTER SET utf8;

CREATE TABLE `user_info`
(
  `id`                   INTEGER PRIMARY KEY NOT NULL AUTO_INCREMENT,
  `user_id`              INTEGER UNIQUE      NOT NULL,
  `name`                 VARCHAR(255)        NOT NULL,
  `surname`              VARCHAR(255)        NOT NULL,
  `about`                TEXT,
  `picture_link`         VARCHAR(255) DEFAULT NULL,
  `email`                VARCHAR(255)        NOT NULL,
  `date_of_birth`        TIMESTAMP    DEFAULT CURRENT_TIMESTAMP,
  `date_of_registration` TIMESTAMP    DEFAULT CURRENT_TIMESTAMP
) ENGINE = INNODB
  DEFAULT CHARACTER SET utf8;


CREATE TABLE `event_info`
(
  `id`           INTEGER PRIMARY KEY NOT NULL AUTO_INCREMENT,
  `name`         VARCHAR(255)        NOT NULL,
  `description`  TEXT,
  `picture_link` VARCHAR(255) DEFAULT NULL,
  `theme`        INTEGER,
  `date`         TIMESTAMP           NOT NULL,
  `address`      INTEGER,
  `author_id`    INTEGER             NOT NULL,
  `capacity`     INTEGER             NOT NULL,
  `duration`     TIME                NOT NULL
) ENGINE = INNODB
  DEFAULT CHARACTER SET utf8;

CREATE TABLE `theme`
(
  `id`    INTEGER PRIMARY KEY NOT NULL AUTO_INCREMENT,
  `value` VARCHAR(255) UNIQUE NOT NULL
) ENGINE = INNODB
  DEFAULT CHARACTER SET utf8;

CREATE TABLE `address`
(
  `id`       INTEGER PRIMARY KEY NOT NULL AUTO_INCREMENT,
  `country`  VARCHAR(255)        NOT NULL,
  `city`     VARCHAR(255)        NOT NULL,
  `street`   VARCHAR(255)        NOT NULL,
  `building` VARCHAR(255)        NOT NULL

) ENGINE = INNODB
  DEFAULT CHARACTER SET utf8;

CREATE TABLE `registrations`
(
  `id`        INTEGER PRIMARY KEY NOT NULL AUTO_INCREMENT,
  `event_id`  INTEGER,
  `user_id`   INTEGER,
  `user_role` INTEGER
) ENGINE = INNODB
  DEFAULT CHARACTER SET utf8;

CREATE TABLE `user_role`
(
  `id`    INTEGER PRIMARY KEY NOT NULL AUTO_INCREMENT,
  `value` VARCHAR(255) UNIQUE NOT NULL
) ENGINE = INNODB
  DEFAULT CHARACTER SET utf8;

ALTER TABLE `user_info`
  ADD FOREIGN KEY (`user_id`) REFERENCES `user` (`id`)
    ON UPDATE CASCADE
    ON DELETE CASCADE;

ALTER TABLE `registrations`
  ADD FOREIGN KEY (`event_id`) REFERENCES `event_info` (`id`)
    ON UPDATE CASCADE
    ON DELETE CASCADE;

ALTER TABLE `registrations`
  ADD FOREIGN KEY (`user_id`) REFERENCES `user` (`id`)
    ON UPDATE CASCADE
    ON DELETE CASCADE;

ALTER TABLE `event_info`
  ADD FOREIGN KEY (`author_id`) REFERENCES `user` (`id`)
    ON UPDATE CASCADE
    ON DELETE CASCADE;

ALTER TABLE `event_info`
  ADD FOREIGN KEY (`address`) REFERENCES `address` (`id`)
    ON UPDATE CASCADE
    ON DELETE SET NULL;

ALTER TABLE `user`
  ADD FOREIGN KEY (`permission`) REFERENCES `permission` (`id`)
    ON UPDATE CASCADE
    ON DELETE SET NULL;

ALTER TABLE `event_info`
  ADD FOREIGN KEY (`theme`) REFERENCES `theme` (`id`)
    ON UPDATE CASCADE
    ON DELETE SET NULL;

ALTER TABLE `registrations`
  ADD FOREIGN KEY (`user_role`) REFERENCES `user_role` (`id`)
    ON UPDATE CASCADE
    ON DELETE SET NULL;