USE `meetups`;

INSERT INTO `user` (
	`id`,
	`login`,
	`password`,
	`permission`
) VALUES (
	1,
	"root1",
	"1234", /* MD5 хэш пароля "user" */
	1
), (
	2,
	"user3",
	"EE11CBB19052E40B07AAC0CA060C23EE", /* MD5 хэш пароля "user" */
	2
), (
	3,
	"user4",
	"EE11CBB19052E40B07AAC0CA060C23EE", /* MD5 хэш пароля "user" */
	2
);