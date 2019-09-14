USE `test`;

INSERT INTO permission (id, name) VALUES (2, 'administrator');
INSERT INTO permission (id, name) VALUES (1, 'user');

INSERT INTO theme (id, value) VALUES (4, 'advertising');
INSERT INTO theme (id, value) VALUES (1, 'business');
INSERT INTO theme (id, value) VALUES (2, 'design');
INSERT INTO theme (id, value) VALUES (3, 'science');

INSERT INTO user_role (id, value) VALUES (1, 'listener');
INSERT INTO user_role (id, value) VALUES (2, 'teller');
INSERT INTO user_role (id, value) VALUES (3, 'author');